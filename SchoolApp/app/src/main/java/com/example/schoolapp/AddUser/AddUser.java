package com.example.schoolapp.AddUser;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.schoolapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class AddUser extends AppCompatActivity {

    public static final int CAMERA_PERMISSION_CODE = 333;
    ImageView imageAdd;
    EditText editName, editEmail, editPassword, editMobile;
    Button buttonAdd;
    Spinner spinner;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    String itemClass;
    private int REQUEST_STORAGE = 111;
    private int REQUEST_FILE = 222;
    private Uri uri;
    private String stringPath;
    private Intent iData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user);

        spinner = findViewById(R.id.spinner);
        buttonAdd = findViewById(R.id.buttonAdd);
        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        editMobile = findViewById(R.id.editMobile);
        imageAdd = findViewById(R.id.imageAdd);

        imageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(AddUser.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_STORAGE);
                } else {
                    SelectImage();
                }


            }
        });

        editPassword.setText(generatePassword(8));
        // this is the code of spinner
        List<String> classes = new ArrayList<>();
        classes.add(0, "Select Class");
        classes.add("Nursery");
        classes.add("L.K.G.");
        classes.add("U.K.G");
        classes.add("I");
        classes.add("II");
        classes.add("III");
        classes.add("IV");
        classes.add("V");
        classes.add("VI");
        classes.add("VII");
        classes.add("VIII");
        classes.add("IX");
        classes.add("X");
        classes.add("XI");
        classes.add("XII");

        // style and populate the spinner
        ArrayAdapter<String> classAdapter;
        classAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, classes);
        //Dropdown layout style
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(classAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select Class")) {
                    Toast.makeText(AddUser.this, "Please Select Some Value", Toast.LENGTH_SHORT).show();

                } else {
                    itemClass = parent.getItemAtPosition(position).toString();


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }



    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,101);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();

            } else {
                Toast.makeText(this, "Camera Permission Is Requires", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void SelectImage() {

        Intent intentSelectImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
       startActivityForResult(intentSelectImage, REQUEST_FILE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_FILE && resultCode == RESULT_OK) {
            if (data != null) {
                uri = data.getData();
                iData = data;
                getStringPath(uri);

                try {
                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    imageAdd.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


            }
        }
    }

    private String getStringPath(Uri myUri) {

        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(myUri, filePathColumn, null, null, null);
        if (cursor == null) {
            stringPath = myUri.getPath();
        } else {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            stringPath = cursor.getString(columnIndex);
            cursor.close();
        }
        return stringPath;

    }

    // this is for random password generate
    private String generatePassword(int length) {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz!@#$%^&*()_-+=<>?/{}~|".toCharArray();
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = chars[r.nextInt(chars.length)];
            sb.append(c);

        }
        return sb.toString();
    }

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    // this is for validate email
    private boolean validateEmail() {
        String emailInput = editEmail.getText().toString().trim();
        if (emailInput.isEmpty()) {
            editEmail.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            editEmail.setError("Please enter a valid email address");
            return false;
        } else {
            editEmail.setError(null);
            return true;
        }
    }

    public void confirmInput(View v) {
        if (!validateEmail()) {
            return;
        }
        //
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("student");

        // Get All Values Here
        String name = editName.getText().toString();
        String email = editEmail.getText().toString();
        String mobile = editMobile.getText().toString();
        String password = editPassword.getText().toString();

        AddUserDataHelper addUserDataHelper = new AddUserDataHelper(name, email, mobile, password, itemClass);
        reference.child(mobile).setValue(addUserDataHelper);


    }
}
