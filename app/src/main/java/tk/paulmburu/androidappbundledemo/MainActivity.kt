package tk.paulmburu.androidappbundledemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
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

    }

    fun loadFeatureOne(view: View) {
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
                // Module download successful
                var intent = Intent().setClassName(packageName,"tk.paulmburu.feature1.FeatureOneActivity")
                startActivity(intent)
            } )
            .addOnFailureListener(OnFailureListener {
                // Module download failed; handle the error here
                Toast.makeText(applicationContext,"Couldn't download feature1: ${it.message}", Toast.LENGTH_LONG).show()
            })
    }

    fun loadFeatureTwo(view: View) {
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

                var intent = Intent().setClassName(packageName,"tk.paulmburu.feature2.FeatureTwoActivity")
                startActivity(intent)
            } )
            .addOnFailureListener(OnFailureListener {
//                Toast.makeText(applicationContext,"$", Toast.LENGTH_LONG).show()
                // Module download failed; handle the error here
                Toast.makeText(applicationContext,"Couldn't download feature2: ${it.message}", Toast.LENGTH_LONG).show()
            })
    }
}
