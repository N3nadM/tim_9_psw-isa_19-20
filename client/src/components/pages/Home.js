import React from "react";
import { connect } from "react-redux";

import AdminKlinikeProfil from "./homeSubpages/ProfilAdminKlinike";
import AdminKlinickogCentraProfil from "./homeSubpages/ProfilAKC";
import PacijentProfil from "./homeSubpages/ProjfilPacijent";
import MedSestraProfil from "./homeSubpages/ProfilMedSestra";
import LekarProfil from "./homeSubpages/ProfilLekar";

const Home = ({ currentUser }) => {
  return (
    <main>
      {currentUser.user.role[0].name === "ROLE_AK" && <AdminKlinikeProfil />}
      {currentUser.user.role[0].name === "ROLE_AKC" && (
        <AdminKlinickogCentraProfil />
      )}
      {currentUser.user.role[0].name === "ROLE_PACIJENT" && <PacijentProfil />}
      {currentUser.user.role[0].name === "ROLE_MED_SESTRA" && (
        <MedSestraProfil />
      )}
      {currentUser.user.role[0].name === "ROLE_LEKAR" && <LekarProfil />}
    </main>
  );
};

function mapStateToProps(state) {
  return {
    currentUser: state.currentUser
  };
}

export default connect(mapStateToProps, null)(Home);
