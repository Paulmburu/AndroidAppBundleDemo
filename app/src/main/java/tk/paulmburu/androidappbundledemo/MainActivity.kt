package tk.paulmburu.androidappbundledemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.tasks.OnFailureListener
import com.google.android.play.core.tasks.OnSuccessListener
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var splitInstallManager : SplitInstallManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        splitInstallManager = SplitInstallManagerFactory.create(applicationContext)

        findViewById<Button>(R.id.button_feature1).setOnClickListener(View.OnClickListener {
            loadFeatureOne()
        })

        findViewById<Button>(R.id.button_feature2).setOnClickListener(View.OnClickListener {
            loadFeatureTwo()
        })
    }

    private fun loadFeatureOne() {
        var request: SplitInstallRequest =
            SplitInstallRequest.
                newBuilder()
        // You can download multiple on demand modules per
        // request by invoking the following method for each
        // module you want to install.
                .addModule("feature1")
                .build()

        // Begin the installation of the feature1 module and handle success/failure
        splitInstallManager
            .startInstall(request)
            .addOnSuccessListener ( OnSuccessListener<Int>(){
                @Override
                fun onsucess(int: Int){
                    // Module download successful
                }
            } )
            .addOnFailureListener(OnFailureListener {
                @Override
                fun onFailure(e: Exception){
                    // Module download failed; handle the error here
                }
            })
    }

    private fun loadFeatureTwo() {
        var request: SplitInstallRequest =
            SplitInstallRequest.
                newBuilder()
                // You can download multiple on demand modules per
                // request by invoking the following method for each
                // module you want to install.
                .addModule("feature2")
                .build()

        // Begin the installation of the feature1 module and handle success/failure
        splitInstallManager
            .startInstall(request)
            .addOnSuccessListener ( OnSuccessListener<Int>(){
                @Override
                fun onsucess(int: Int){
                    // Module download successful
                }
            } )
            .addOnFailureListener(OnFailureListener {
                @Override
                fun onFailure(e: Exception){
                    // Module download failed; handle the error here
                }
            })
    }
}
