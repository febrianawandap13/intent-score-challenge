package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    ImageView homeImage, awayImage;
    EditText homeText, awayText;
    Uri imageUri1;
    Uri imageUri2;
    Bitmap bitmap1;
    Bitmap bitmap2;
    private static final String TAG = MainActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO
        //Fitur Main Activity
        //1. Validasi Input Home Team
        //2. Validasi Input Away Team
        //5. Next Button Pindah Ke MatchActivity
        homeImage = findViewById(R.id.home_logo);
        awayImage = findViewById(R.id.away_logo);
        homeText = findViewById(R.id.home_team);
        awayText = findViewById(R.id.away_team);

    }

    //3. Ganti Logo Home Team
    public void handleHomeLogo(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    //4. Ganti Logo Away Team
    public void handleAwayLogo(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }
    //gallery or camera
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        if (requestCode == 1) {
            if (data != null) {
                try {
                    imageUri1 = data.getData();
                    bitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri1);
                    homeImage.setImageBitmap(bitmap1);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't Load Image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        } else if (requestCode == 2) {
            if (data != null) {
                try {
                    imageUri2 = data.getData();
                    bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri2);
                    awayImage.setImageBitmap(bitmap2);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't Load Image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }

    public void handleNext(View view) {
        String inputHome = homeText.getText().toString();
        String inputAway = awayText.getText().toString();
        if (!inputAway.equals("") && !inputHome.equals("") && bitmap1 != null && bitmap2 != null) {
            Intent intent = new Intent(this, MatchActivity.class);
            intent.putExtra("inputHome", inputHome);
            intent.putExtra("inputAway", inputAway);
            intent.putExtra("logoHome", imageUri1.toString());
            intent.putExtra("logoAway", imageUri2.toString());

            startActivity(intent);

        }else {
            Toast.makeText(this, "Isi seluruh data terlebih dahulu!", Toast.LENGTH_SHORT).show();
        }

    }
}
