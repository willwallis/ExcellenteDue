package it.jaschke.alexandria;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;


public class ScanActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        // Wiring up the Button
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    // Load the Image
    ImageView myImageView = (ImageView) findViewById(R.id.imgview);
    Bitmap myBitmap = BitmapFactory.decodeResource(
            getApplicationContext().getResources(),
            R.drawable.puppy);
    myImageView.setImageBitmap(myBitmap);

    // Setup the Barcode Detector
    TextView txtView = (TextView) findViewById(R.id.txtContent);
    BarcodeDetector detector =
            new BarcodeDetector.Builder(getApplicationContext())
                    .setBarcodeFormats(Barcode.DATA_MATRIX | Barcode.QR_CODE)
                    .build();
    if(!detector.isOperational()){
        txtView.setText(R.string.detector_failure);
        return;
    }

    // Detect the barcode
    Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
    SparseArray<Barcode> barcodes = detector.detect(frame);

    // Decode the barcode
    Barcode thisCode = barcodes.valueAt(0);
    txtView.setText(thisCode.rawValue);
    }

}
