package di.mutibodroid;


import di.mutibodroid.MutiboResults;

/**
 * Interface defining the method that the AcronymServiceAsync will
 * implement to provide access to the Acronym Web service.
 */
interface MutiboRequest {
   /**
    * A one-way (non-blocking) call to the MutiboService that
    * retrieves information about a Trivia Movie Set  the Mutibo Web
    * service.  The MutiboService subsequently uses the
    * MutiboResults parameter to return a List of AcronymData
    * containing the results from the Web service back to the
    * FilmListActivity.
    */
    oneway void getTriviaSet (in MutiboResults results); 
}
