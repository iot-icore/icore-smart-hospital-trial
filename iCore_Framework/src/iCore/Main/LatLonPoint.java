package iCore.Main;

/**
 * The Class LatLonPoint.
 *
 * @author Nicola Dorigatti
 */
public class LatLonPoint {

	/** The longitude. */
	protected double longitude = 0d;
	
	/** The latitude. */
	protected double latitude = 0d;
	
	/** The altitude. */
	protected double altitude = 0.5d;

	/**
	 * Instantiates a new lat lon point.
	 *
	 * @param latitude the latitude
	 * @param longitude the longitude
	 */
	public LatLonPoint(double latitude, double longitude) {
		this(latitude, longitude, 0.5d);
	}

	/**
	 * Instantiates a new lat lon point.
	 *
	 * @param latitude the latitude
	 * @param longitude the longitude
	 * @param altitude the altitude
	 */
	public LatLonPoint(double latitude, double longitude, double altitude) {
		this.latitude = latitude;
		this.longitude = longitude;
		if (altitude < 0) {
			altitude = 0;
		}
		this.altitude = altitude;		
	}

	/**
	 * Gets the longitude.
	 *
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * Gets the latitude.
	 *
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * Gets the altitude.
	 *
	 * @return the altitude
	 */
	public double getAltitude() {
		return altitude;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LatLonPoint [longitude=");
		builder.append(longitude);
		builder.append(", latitude=");
		builder.append(latitude);
		builder.append(", altitude=");
		builder.append(altitude);
		builder.append("]");
		return builder.toString();
	}

}
