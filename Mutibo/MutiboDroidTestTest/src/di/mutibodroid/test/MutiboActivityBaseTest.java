package di.mutibodroid.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.content.Intent;
import android.os.Bundle;
import android.test.ActivityUnitTestCase;
import di.mutibo.server.TriviaSet;
import di.mutibodroid.FilmListFragment;
import di.mutibodroid.MutiboActivityBase;
import di.mutibodroid.R;
import di.mutibodroid.TriviaSetData;

public class MutiboActivityBaseTest extends ActivityUnitTestCase<MutiboActivityBasWrapper> {

	public MutiboActivityBaseTest(Class<MutiboActivityBasWrapper> activityClass) {
		super(activityClass);
		// TODO Auto-generated constructor stub
	}

	MutiboActivityBasWrapper mMutiboActivityBase;
	@Override
    protected void setUp() throws Exception {
        super.setUp();
        Intent mLaunchIntent = new Intent(getInstrumentation()
                .getTargetContext(), MutiboActivityBasWrapper.class);
        startActivity(mLaunchIntent, null, null);
        
    }

	@After
	public void tearDown() throws Exception {
		
		mMutiboActivityBase = null;
	}

	@Test
	public final void testOnStart() {
		fail("Not yet implemented");
	}

	@Test
	public final void testOnStop() {
		fail("Not yet implemented");
	}

	@Test
	public final void testRefreshTriviaSet() {
		mMutiboActivityBase.refreshTriviaSet();
		TriviaSet ts = mMutiboActivityBase.getTriviaSet();
		assertNotNull(ts);
	}

	@Test
	public final void testDisplayResults() {
		fail("Not yet implemented");
	}

	

}

class MutiboActivityBasWrapper extends MutiboActivityBase{
	private TriviaSet mTriviaSet ;
	
	public MutiboActivityBasWrapper(){
		super();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(di.mutibodroid.test.R.layout.empty_view);
	}

	
	@Override
	protected void displayResults(List<TriviaSetData> results) {
		// TODO Auto-generated method stub
		if(results.isEmpty()){
			fail("No data returned by the Server");
			return;
		}
		
		mTriviaSet = results.get(0).getTriviaSet();
		assertNotNull(mTriviaSet);
	}
	
	public void refreshTriviaSet(){
		super.refreshTriviaSet();
	}
	
	public TriviaSet getTriviaSet(){
		return mTriviaSet;
	}
	
}