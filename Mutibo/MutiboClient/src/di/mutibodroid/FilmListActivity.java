package di.mutibodroid;

import java.util.List;

import di.mutibo.server.TriviaSet;
import di.mutibodroid.R;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

/**
 * An activity representing a list of Films. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link FilmDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link FilmListFragment} and the item details (if present) is a
 * {@link FilmDetailFragment}.
 * <p>
 * This activity also implements the required {@link FilmListFragment.Callbacks}
 * interface to listen for item selections.
 */
public class FilmListActivity extends MutiboActivityBase implements
		FilmListFragment.Callbacks {

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_film_list);

		if (findViewById(R.id.film_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;

			// In two-pane mode, list items should be given the
			// 'activated' state when touched.
			((FilmListFragment) getFragmentManager().findFragmentById(
					R.id.film_list)).setActivateOnItemClick(true);
		}

		// TODO: If exposing deep links into your app, handle intents here.
		
	}
	@Override 
	public void onStart(){
		super.onStart();
	}
	@Override
	public void onStop() {
        super.onStop();
    }

	/**
	 * Callback method from {@link FilmListFragment.Callbacks} indicating that
	 * the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(String id) {
		if (mTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(FilmDetailFragment.ARG_ITEM_ID, id);
			FilmDetailFragment fragment = new FilmDetailFragment();
			fragment.setArguments(arguments);
			getFragmentManager().beginTransaction()
					.replace(R.id.film_detail_container, fragment).commit();

		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			Intent detailIntent = new Intent(this, FilmDetailActivity.class);
			detailIntent.putExtra(FilmDetailFragment.ARG_ITEM_ID, id);
			startActivity(detailIntent);
		}
		super.refreshTriviaSet();
	}

	@Override
	protected void displayResults(List<TriviaSetData> results) {
		if(!results.isEmpty()){
			TriviaSet triviaSet = results.get(0).getTriviaSet();
		}
		
	}
}
