package come.wolfpack.sigmapdrone;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by max on 10/18/17.
 */

public class FbHelper {

    private DatabaseReference database;

    public FbHelper() {
        database = FirebaseDatabase.getInstance().getReference();
    }

    public void writeData() {
        database.push().setValue("testing");
    }
}
