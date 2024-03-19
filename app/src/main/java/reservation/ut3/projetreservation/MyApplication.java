package reservation.ut3.projetreservation;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize Firebase
        FirebaseApp.initializeApp(this);
        // Enable disk persistence (optional)
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

}
