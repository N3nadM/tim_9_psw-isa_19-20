import React from "react";
import { connect } from "react-redux";

import AdminKlinikeProfil from "./homeSubpages/ProfilAdminKlinike";
import AdminKlinickogCentraProfil from "./homeSubpages/ProfilAKC";
import PacijentProfil from "./homeSubpages/ProjfilPacijent";
import MedSestraProfil from "./homeSubpages/ProfilMedSestra";
import LekarProfil from "./homeSubpages/ProfilLekar";
import PromeniLozinku from "../Tabs/Korisnik/PromeniLozinku";
import AppBar from "../layout/AppBar";

const Home = ({ currentUser }) => {
  let render;

  switch (currentUser.user.role[0].name) {
    case "ROLE_AK":
      render = <AdminKlinikeProfil />;
      break;
    case "ROLE_AKC":
      render = <AdminKlinickogCentraProfil />;
      break;
    case "ROLE_PACIJENT":
      render = <PacijentProfil />;
      break;
    case "ROLE_MED_SESTRA":
      render = <MedSestraProfil />;
      break;
    case "ROLE_LEKAR":
      render = <LekarProfil />;
      break;
    default:
      render = <PacijentProfil />;
  }

  return (
    <main>
      {currentUser.user.role[0].name !== "ROLE_PACIJENT" &&
      currentUser.user.firstLogin ? (
        <>
          <AppBar />
          <PromeniLozinku />
        </>
      ) : (
        render
      )}
    </main>
  );
};

function mapStateToProps(state) {
  return {
    currentUser: state.currentUser
  };
}

export default connect(mapStateToProps, null)(Home);
