package di.mutibodroid;

import di.mutibodroid.TriviaSetData;
import java.util.List;

/**
 * Interface defining the method that receives callbacks from the
 * MutiboService
 */
interface MutiboResults {
    /**
     * This one-way (non-blocking) method allows the
     * MutiboService to return the List of TriviaSetData results
     * associated with a one-way MutiboRequest.callMutiboRequest()
     * call.
     */
    oneway void sendResults(in List<TriviaSetData> results);
}
