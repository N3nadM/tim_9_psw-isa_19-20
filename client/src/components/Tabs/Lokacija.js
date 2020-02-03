//import Geolocation from "@react-native-community/geolocation";
import React, { useEffect } from "react";
import { connect } from "react-redux";
import { Paper } from "@material-ui/core";

const Mapa = ({ adresa }) => {
  return (
    <Paper style={{ padding: 50, paddingBottom: 75 }}>
      <div
        id="map-container"
        className="rounded z-depth-1-half map-container"
        style={{ height: "400px" }}
      >
        <iframe
          src={`https://www.google.com/maps/embed/v1/place?key=AIzaSyC-QqGOI8Del5pUKi9k37ofcQxxeZfUDCw&q=${adresa}`}
          style={{ height: "100%" }}
          width="100%"
          height="100%"
          title="Mapa gde se nalazimo"
          frameBorder="0"
          style={{ border: 0 }}
        />
      </div>
    </Paper>
  );
};

function mapStateToProps(state) {
  return {};
}

export default connect(mapStateToProps, {})(Mapa);
