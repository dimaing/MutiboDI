package di.mutibodroid;

import java.util.ArrayList;




import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;


public class MutiboService extends Service {

	 /**
     * The concrete implementation of the AIDL Interface
     * MutiboRequest, which extends the Stub class that implements
     * MutiboRequest, thereby allowing Android to handle calls across
     * process boundaries.  This method runs in a separate Thread as
     * part of the Android Binder framework.
     * 
     * This implementation plays the role of Invoker in the Broker
     * Pattern.
     */
    MutiboRequest.Stub mMutiboRequestImpl = new MutiboRequest.Stub() {
            /**
             * Implement the AIDL AcronymRequest expandAcronym()
             * method, which forwards to DownloadUtils getResults() to
             * obtain the results from the Mutibo Web service and
             * then sends the results back to the Activity via a
             * callback.
             */
            @Override
            public void getTriviaSet(MutiboResults callback)
                throws RemoteException {
                 TriviaSetData triviaSet = new TriviaSetData();
                 try {
					triviaSet.setTriviaSet(MutiboUtils.getTriviaSet());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                 ArrayList<TriviaSetData> result = new  ArrayList<TriviaSetData>();
                 result.add(triviaSet);

                callback.sendResults(result);
            }
	};

    /**
     * Called when a client (e.g., AcronymActivity) calls
     * bindService() with the proper Intent.  Returns the
     * implementation of MutiboRequest, which is implicitly cast as
     * an IBinder.
     */
    @Override
    public IBinder onBind(Intent intent) {
        return mMutiboRequestImpl;
    }

    /**
     * Factory method that makes an Intent used to start the
     * MutiboService when passed to bindService().
     * 
     * @param context
     *            The context of the calling component.
     */
    public static Intent makeIntent(Context context) {
        return new Intent(context,
        		MutiboService.class);
    }

}
