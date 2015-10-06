package org.opentripplanner.index;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Envelope;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.opentripplanner.geocoder.GeocoderResult;
import org.opentripplanner.geocoder.GeocoderResults;
import org.opentripplanner.geocoder.nominatim.NominatimGeocoder;
import org.opentripplanner.standalone.OTPServer;

@Path("/routers/{routerId}/nominatim")
@Produces(MediaType.APPLICATION_XML)
public class NominatimGeocoderResource {
    private final NominatimGeocoder geocoder;

    public NominatimGeocoderResource(@Context OTPServer otpServer, @PathParam("routerId") String routerId) {
        geocoder = new NominatimGeocoder();
        geocoder.setNominatimUrl("http://nominatim.openstreetmap.org/search");
        geocoder.setViewBox("11.0351,44.6301,11.7327,44.3371");
        //System.out.println("NOMINATIM GEOCODER");
    }

    @GET
    public Response textSearch (@QueryParam("query") String query) {
        //System.out.println("QUERY: " + query);
        GeocoderResults results = geocoder.geocode(query, null);
//        for (GeocoderResult result : results.getResults()) {
//            System.out.println("RESULT: " + result.getDescription());
//        }
        
        return Response.status(Response.Status.OK).entity(results).build();
    }
}
