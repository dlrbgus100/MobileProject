package kr.ac.kumoh.ce.s20100884.database;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class PetRegister extends AppCompatActivity {
    Button registerBtn, imageBtn;
    EditText eType, eSize, ePrice, eTerm, eMemo;
    ImageView image;
    private static final int PICK_FROM_CAMERA = 1;
//    private static final int PICK_FROM_GALLERY = 2;
    String picturePath;
    private Uri mImageCaptureUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_register);

        registerBtn = (Button) findViewById(R.id.regiterbtn);
        eType = (EditText) findViewById(R.id.petType);
        eSize = (EditText) findViewById(R.id.petSize);
        ePrice = (EditText) findViewById(R.id.price);
        eTerm = (EditText) findViewById(R.id.term);
        eMemo = (EditText) findViewById(R.id.petMemo);
        image = (ImageView)findViewById(R.id.petImage);
        imageBtn = (Button)findViewById(R.id.imageButton);
        registerBtn.setOnClickListener(buttonClick);

        imageBtn.setOnClickListener(imbtn);
    }

    Button.OnClickListener buttonClick = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {
            Member member = (Member) getApplication();

            String type = eType.getText().toString();
            String size = eSize.getText().toString();
            String price = ePrice.getText().toString();
            String term = eTerm.getText().toString();
            String id = member.getId();
            String memo = eMemo.getText().toString();

            insertToDatabase(type, size, price, term, id, memo);
        }
    };

    private void insertToDatabase(final String type, String size, String price, String term, String id, String memo) {

        class InsertData extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if(s.equals("dup"))
                    Toast.makeText(getApplicationContext(), "이미 등록되어있습니다",Toast.LENGTH_SHORT).show();
                else if(s.equals("noInput"))
                    Toast.makeText(getApplicationContext(), "모두 입력하세요",Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(getApplicationContext(), "등록완료", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }

            @Override
            protected String doInBackground(String... params) {

                try {
                    String type = (String) params[0];
                    String size = (String) params[1];
                    String price = (String) params[2];
                    String term = (String) params[3];
                    String id = (String) params[4];
                    String memo = (String) params[5];

                    String link = "http://117.20.203.48/registerPet.php";

                    String data = URLEncoder.encode("type", "euc-kr") + "=" + URLEncoder.encode(type, "euc-kr");
                    data += "&" + URLEncoder.encode("size", "euc-kr") + "=" + URLEncoder.encode(size, "euc-kr");
                    data += "&" + URLEncoder.encode("price", "euc-kr") + "=" + URLEncoder.encode(price, "euc-kr");
                    data += "&" + URLEncoder.encode("term", "euc-kr") + "=" + URLEncoder.encode(term, "euc-kr");
                    data += "&" + URLEncoder.encode("id", "euc-kr") + "=" + URLEncoder.encode(id, "euc-kr");
                    data += "&" + URLEncoder.encode("memo", "euc-kr") + "=" + URLEncoder.encode(memo, "euc-kr");

//                    String boundary = "****";

                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    conn.setDoInput(true);
//                    conn.setRequestProperty("Content-Type", "multipart/form-data; boundary="+boundary);

                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream(), "euc-kr");
//                    DataOutputStream wr= new DataOutputStream(conn.getOutputStream());
//                    wr.writeBytes("Content-Disposition; form-data; name=\"image\";" +
//                            "filename=\""+id+"\"\r\n");
//                    wr.writeBytes("Content-Type: application/octet-stream\r\n\r\n");
//
//                    FileInputStream fileInputStream = new FileInputStream(mImageCaptureUri.getPath());
//                    int byteAvailable = fileInputStream.available();
//                    int maxBufferSize = 1024;
//                    int bufferSize = Math.min(byteAvailable, maxBufferSize);
//                    byte[] buffer = new byte[bufferSize];
//
//                    int byteRead = fileInputStream.read(buffer, 0 , bufferSize);
//                    while(byteRead > 0){
//                        DataOutputStream dataWrite = new DataOutputStream(conn.getOutputStream());
//                        dataWrite.write(buffer, 0, bufferSize);
//                        byteAvailable = fileInputStream.available();
//                        bufferSize = Math.min(byteAvailable, maxBufferSize);
//                        byteRead = fileInputStream.read(buffer, 0 ,bufferSize);
//                    }
//
//                    fileInputStream.close();
//                    wr.writeBytes("Content-Type: application/octet-stream\r\n\r\n");

//                    wr.writeBytes("\r\n--" + boundary + "--\r\n");
//                    wr.flush();

//                    FileInputStream fileInputStream = new FileInputStream(picturePath);
//                    int bytesAvailable = fileInputStream.available();
//                    int maxBufferSize = 1024;
//                    int bufferSize = Math.min(bytesAvailable, maxBufferSize);
//                    byte[] buffer = new byte[bufferSize];
//
//                    int bytesRead = fileInputStream.read(buffer, 0, bufferSize);
//                    while(bytesRead>0){
//                        DataOutputStream dataWrite = new DataOutputStream(conn.getOutputStream());
//                        dataWrite.write(buffer, 0 ,bufferSize);
//                        bytesAvailable = fileInputStream.available();
//                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
//                        bytesRead = fileInputStream.read(buffer, 0 ,bufferSize);
//                    }
//                    fileInputStream.close();



                    wr.write(data);
                    wr.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "euc-kr"));

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    // Read Server Response
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    return sb.toString();
                } catch (Exception e) {
                    return new String("Exception: " + e.getMessage());
                }

            }
        }

        InsertData task = new InsertData();
        task.execute(type, size, price, term, id, memo);
    }

    Button.OnClickListener imbtn = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {

            DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    doTakePhotoAction();
                }
            };

//            DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    doTakeAlbumAction();
//                }
//            };

            DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            };

            new AlertDialog.Builder(PetRegister.this)
                    .setTitle("업로드할 이미지 선택")
                    .setPositiveButton("사진촬영", cameraListener)
//                    .setNeutralButton("앨범선택", albumListener)
                    .setNegativeButton("취소", cancelListener)
                    .show();
        }

    };
    private void doTakePhotoAction(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

//        mImageCaptureUri = createSaveCropFile();
        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString());

//        intent.putExtra("crop", "true");
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
//        intent.putExtra("outputX", 90);
//        intent.putExtra("outputY", 90);
//        intent.putExtra("output", mImageCaptureUri);
        try {
            intent.putExtra("return-data", true);
            startActivityForResult(intent, PICK_FROM_CAMERA);
        } catch (ActivityNotFoundException e) {
            // Do nothing for now
        }
    }

//    private void doTakeAlbumAction(){
//        Intent intent = new Intent();
//        // Gallery 호출
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        // 잘라내기 셋팅
//        intent.putExtra("crop", "true");
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
//        intent.putExtra("outputX", 90);
//        intent.putExtra("outputY", 90);
//
//        try {
//            intent.putExtra("return-data", true);
//            startActivityForResult(Intent.createChooser(intent,
//                    "Complete action using"), PICK_FROM_GALLERY);
//        } catch (ActivityNotFoundException e) {
//            // Do nothing for now
//        }



    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {

        if (requestCode == PICK_FROM_CAMERA) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap photo = extras.getParcelable("data");
                image.setImageBitmap(photo);
                mImageCaptureUri = data.getData();
                Cursor c = getContentResolver().query(Uri.parse(mImageCaptureUri.toString()), null, null, null, null);
                c.moveToNext();
                picturePath = c.getString(c.getColumnIndex(MediaStore.MediaColumns.DATA));

//                Uri selectedImage = data.getData();
//                String[] filePathColumn = {MediaStore.Images.Media.DATA};
//                Cursor c = getContentResolver().query(selectedImage, filePathColumn,null,null,null);
//                c.moveToFirst();
//
//                int columnIndex = c.getColumnIndex(filePathColumn[0]);
//                picturePath = c.getString(columnIndex);
//                c.close();
            }
        }
//        if (requestCode == PICK_FROM_GALLERY) {
//            Bundle extras2 = data.getExtras();
//            if (extras2 != null) {
//                Bitmap photo = extras2.getParcelable("data");
//                image.setImageBitmap(photo);
//                Uri selectedImage = data.getData();
//                String[] filePathColumn = {MediaStore.Images.Media.DATA};
//                Cursor c = getContentResolver().query(selectedImage, filePathColumn,null,null,null);
//                c.moveToFirst();
//
//                int columnIndex = c.getColumnIndex(filePathColumn[0]);
//                picturePath = c.getString(columnIndex);
//                c.close();
//            }
//        }
    }
//    private Uri createSaveCropFile(){
//        Member member = (Member)getApplicationContext();
//        Uri uri;
//        String url = member.getId() + ".jpg";
//        uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),url));
//        return uri;
//    }
//
//    private File getImageFile(Uri uri) {
//        String[] projection = {MediaStore.Images.Media.DATA};
//        if (uri == null) {
//            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//        }
//        Cursor mCursor = getContentResolver().query(uri, projection, null, null, MediaStore.Images.Media.DATE_MODIFIED + " desc");
//        if (mCursor == null || mCursor.getCount() < 1)
//            return null;
//        int column_index = mCursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        mCursor.moveToFirst();
//
//        String path = mCursor.getString(column_index);
//
//        if (mCursor != null) {
//            mCursor.close();
//            mCursor = null;
//        }
//        return new File(path);
//    }
}
