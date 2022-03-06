package geolocator;

import feign.Feign;
import feign.Param;
import feign.RequestLine;
import feign.jackson.JacksonDecoder;

/**
 * Interface for obtaining geolocation information of an IP address. The class uses the
 * <a href="https://ip-api.com/">IP-API.com</a> service. The {@code static} interface method {@link #newInstance}
 * is provided to obtain a {@code Geolocator} object.
 */

public interface GeoLocator {

    /**
     * Returns geolocation information about the JVM running the application.
     *
     * @return an object wrapping the geolocation information returned
     * @throws feign.FeignException if an error occurs
     */

    @RequestLine("GET")
    GeoLocation getGeoLocation();

    /**
     * Returns geolocation information about the IP address or host name specified.
     *
     * @param ipOrHostName the IPaddress or host name
     * @return an object wrapping the geolocation information returned
     * @throws feign.FeignException if an error occurs
     */
    @RequestLine("GET /{ipOrHostName}")
    GeoLocation getGeoLocation(@Param("ipOrHostName") String ipOrHostName);

    /**
     * Creates and returns an object that implements the interface.
     *
     * @return the object that implements the interface
     */
    static GeoLocator newInstance() {
        return Feign.builder()
            .decoder(new JacksonDecoder())
            .target(GeoLocator.class, "http://ip-api.com/json/");
    }

}
