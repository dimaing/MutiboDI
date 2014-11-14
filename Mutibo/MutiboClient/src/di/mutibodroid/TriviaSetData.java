package di.mutibodroid;


import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import android.os.Parcel;
import android.os.Parcelable;
import di.mutibo.server.TriviaSet;

public class TriviaSetData implements Parcelable {

	private TriviaSet triviaSet = null;
	private TriviaSetData(Parcel in) {
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			setTriviaSet((TriviaSet) mapper.reader(TriviaSet.class).readValue(in.readString()));
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	 public TriviaSetData() {
		// TODO Auto-generated constructor stub
	}

	/**
     * The toString() custom implementation.
     */
    @Override
    public String toString() {
        try {
			return  (new ObjectMapper()).writeValueAsString(getTriviaSet());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.toString());

	}
	public TriviaSet getTriviaSet() {
		return triviaSet;
	}

	public void setTriviaSet(TriviaSet triviaSet) {
		this.triviaSet = triviaSet;
	}
	/**
     * public Parcelable.Creator for TriviaSetData, which is an
     * interface that must be implemented and provided as a public
     * CREATOR field that generates instances of your Parcelable class
     * from a Parcel.
     */
    public static final Parcelable.Creator<TriviaSetData> CREATOR =
        new Parcelable.Creator<TriviaSetData>() {
        public TriviaSetData createFromParcel(Parcel in) {
            return new TriviaSetData(in);
        }

        public TriviaSetData[] newArray(int size) {
            return new TriviaSetData[size];
        }
    };
}
