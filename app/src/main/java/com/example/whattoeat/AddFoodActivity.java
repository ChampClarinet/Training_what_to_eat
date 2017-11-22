package com.example.whattoeat;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.whattoeat.DB.DatabaseHelper;
import com.kbeanie.imagechooser.api.ChooserType;
import com.kbeanie.imagechooser.api.ChosenImage;
import com.kbeanie.imagechooser.api.ChosenImages;
import com.kbeanie.imagechooser.api.ImageChooserListener;
import com.kbeanie.imagechooser.api.ImageChooserManager;
import com.kbeanie.imagechooser.exceptions.ChooserException;

public class AddFoodActivity extends AppCompatActivity implements ImageChooserListener {

    private static final String TAG = AddFoodActivity.class.getSimpleName();

    private EditText mFoodNameEditText;
    private ImageView mFoodImageView;
    private Button mAddButton;

    private String mPictucrePath;

    private DatabaseHelper mHelper;
    private SQLiteDatabase mDB;

    private ImageChooserManager mImageChooserManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        mFoodNameEditText = findViewById(R.id.food_name_edit_text);
        mFoodImageView = findViewById(R.id.food_image_view);
        mAddButton = findViewById(R.id.add_button);

        mHelper = new DatabaseHelper(this);
        mDB = mHelper.getWritableDatabase();

        mFoodImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] items = {"เลือกภาพจากอัลบั้ม", "ถ่ายภาพ"};

                new AlertDialog.Builder(AddFoodActivity.this)
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0: // choose image
                                        chooseImage();
                                        break;
                                    case 1: // take photo
                                        takePicture();
                                        break;
                                }
                            }
                        })
                        .show();
            }
        });

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String foodName = mFoodNameEditText.getText().toString();

                if (isFoodNameEditTextEmpty()) {
                    Toast.makeText(AddFoodActivity.this, "Please type the food name.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(isPicturePathEmpty()){
                    mPictucrePath = "default.jpg";
                }

                /*if (isPicturePathEmpty()) {
                    Toast.makeText(AddFoodActivity.this, "Please select the food picture.", Toast.LENGTH_SHORT).show();
                    return;
                }*/

                ContentValues cv = new ContentValues();
                cv.put(DatabaseHelper.COL_NAME, foodName);
                cv.put(DatabaseHelper.COL_PICTURE, mPictucrePath);

                if (mDB.insert(DatabaseHelper.TABLE_NAME, null, cv) > 0) { // returns the row ID of the newly inserted row, or -1 if an error occurred
                    Toast.makeText(AddFoodActivity.this, foodName+" "+getString(R.string.added), Toast.LENGTH_SHORT).show();
                    finish();
                }else Toast.makeText(AddFoodActivity.this, foodName+" "+getString(R.string.failed), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private boolean isPicturePathEmpty(){
        return mPictucrePath == null;
    }

    private boolean isFoodNameEditTextEmpty(){
        return mFoodNameEditText.getText().toString().length() == 0;
    }

    private void chooseImage() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(Intent.EXTRA_ALLOW_MULTIPLE, false);

        mImageChooserManager = new ImageChooserManager(
                this,
                ChooserType.REQUEST_PICK_PICTURE,
                true
        );
        mImageChooserManager.setExtras(bundle);
        mImageChooserManager.setImageChooserListener(this);
        mImageChooserManager.clearOldFiles();

        try {
            mImageChooserManager.choose();
        } catch (ChooserException e) {
            e.printStackTrace();
        }
    }

    private void takePicture() {
        mImageChooserManager = new ImageChooserManager(
                this,
                ChooserType.REQUEST_CAPTURE_PICTURE,
                true
        );
        mImageChooserManager.setImageChooserListener(this);

        try {
            mImageChooserManager.choose();
        } catch (ChooserException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent returnedIntent) {
        super.onActivityResult(requestCode, resultCode, returnedIntent);

        if (resultCode == RESULT_OK
                && (requestCode == ChooserType.REQUEST_PICK_PICTURE
                || requestCode == ChooserType.REQUEST_CAPTURE_PICTURE)) {
            if (mImageChooserManager == null) {
                //reinitializeImageChooser();
                return;
            }
            mImageChooserManager.submit(requestCode, returnedIntent);
        }
    }

    @Override
    public void onImageChosen(final ChosenImage image) {
        new Handler(Looper.getMainLooper()).post(
                new Runnable() {
                    @Override
                    public void run() {
                        if (image != null) {
                            //mChosenImage = image;

                            mPictucrePath = image.getFilePathOriginal();
                            //Log.i(TAG, "-----");
                            //Log.i(TAG, "Image path: " + filePathOriginal);
                            //Log.i(TAG, "Image thumbnail path: " + image.getFileThumbnail());
                            //Log.i(TAG, "Image small thumbnail path: " + image.getFileThumbnailSmall());

                            Glide.with(AddFoodActivity.this)
                                    .load(mPictucrePath)
                                    .into(mFoodImageView);
                        } else {
                            //mChosenImage = null;
                            Log.i(TAG, "Image is NULL !?!");
                        }
                    }
                }
        );
    }

    @Override
    public void onError(String s) {

    }

    @Override
    public void onImagesChosen(ChosenImages chosenImages) {

    }
}