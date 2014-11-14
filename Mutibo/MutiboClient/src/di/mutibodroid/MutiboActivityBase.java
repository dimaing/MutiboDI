package di.mutibodroid;

import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;


public abstract class MutiboActivityBase extends Activity {
	 /**
     * Reference to the Mutibo Service (after it's bound).
     */
    protected MutiboRequest mMutiboRequest;
    
    MutiboResults.Stub mMutiboResults = new MutiboResults.Stub() {
        /**
         * This method is called back by the Service to return the
         * results.
         */
		@Override
		public void sendResults(final List<TriviaSetData> results)
				throws RemoteException {
            // This method runs in a separate Thread as per the
            // behavior of the Android Binder framework, so we
            // need to explicitly post a runnable containing the
            // results back to the UI Thread.
            runOnUiThread(new Runnable() {
                    public void run() {
                        displayResults(results);
                    }

                });
		}
    };
    
    
    /**
     * This ServiceConnection is used to receive results after binding
     * to the MutiboService Service using bindService().
     */
    protected ServiceConnection mServiceConnectionAsync = new ServiceConnection() {
            /**
             * Cast the returned IBinder object to the AcronymRequest
             * AIDL Interface and store it for later use in
             * mAcronymRequest.
             */
	        @Override
			public void onServiceConnected(ComponentName name, IBinder service) {

                mMutiboRequest = MutiboRequest.Stub.asInterface(service);
            }

            /**
             * Called if the remote service crashes and is no longer
             * available. The ServiceConnection will remain bound, but
             * the service will not respond to any requests.
             */
	        @Override
			public void onServiceDisconnected(ComponentName name) {
            	mMutiboRequest = null;
            }

	};

    
	 /**
     * Hook method called when the MainActivity becomes visible to bind the
     * Activity to the Services.
     */
    @Override
    public void onStart() {
        super.onStart();

        // Launch the designated Bound Service(s) if they aren't
        // already running via a call to bindService(), which binds
        // this activity to the Mutibo* Services if they aren't
        // already bound.
        if (mMutiboRequest == null) {
            bindService(MutiboService.makeIntent(this),
                        mServiceConnectionAsync, BIND_AUTO_CREATE);
        }
    }

    /**
     * Hook method called when the MainActivity becomes completely hidden to
     * unbind the Activity from the Services.
     */
    @Override
	public void onStop() {
        super.onStop();

        // Unbind the Async Service if it is connected.
        if (mMutiboRequest != null) {
            unbindService(mServiceConnectionAsync);
        }
    }

    protected void refreshTriviaSet(){
    	try {
    		if(mMutiboRequest != null)
    			mMutiboRequest.getTriviaSet(mMutiboResults);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /*
     * Display data to be implemented in concrete class
     */
    protected abstract void displayResults(List<TriviaSetData> results);
}
