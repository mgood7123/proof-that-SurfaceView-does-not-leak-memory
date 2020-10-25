package smallville7123.surface.view.test;

import androidx.appcompat.app.AppCompatActivity;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    SurfaceView surfaceView;
    FrameLayout frameLayout;

    Boolean running = Boolean.FALSE;

    Thread thread = new Thread(() -> {
        try {
            while(true) {
                synchronized (running) {
                    if (running) {
                        runOnUiThread(() -> frameLayout.addView(surfaceView));
                        sleep(75);
                        runOnUiThread(() -> frameLayout.removeView(surfaceView));
                    }
                }
                sleep(75);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        surfaceView = new MyGLSurfaceView(this);
        frameLayout = findViewById(R.id.frameLayout);
        thread.start();
        findViewById(R.id.button).setOnClickListener(v -> {
            synchronized (running) {
                running = Boolean.TRUE;
            }
        });
        findViewById(R.id.button2).setOnClickListener(v -> {
            synchronized (running) {
                running = Boolean.FALSE;
            }
        });
    }
}