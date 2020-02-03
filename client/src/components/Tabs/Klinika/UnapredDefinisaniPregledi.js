import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import Paper from "@material-ui/core/Paper";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableHead from "@material-ui/core/TableHead";
import TablePagination from "@material-ui/core/TablePagination";
import TableRow from "@material-ui/core/TableRow";
import { connect } from "react-redux";
import {
  getPredefinisaniPregledi,
  rezervisiPregled
} from "../../../store/actions/pregled";
import Typography from "@material-ui/core/Typography";
import Button from "@material-ui/core/Button";
import Tooltip from "@material-ui/core/Tooltip";
import TableSortLabel from "@material-ui/core/TableSortLabel";

const useStyles = makeStyles({
  root: {
    width: "100%"
  },
  tableWrapper: {
    overflow: "auto"
  }
});

const UnapredDefinisaniPregledi = ({
  klinikaId,
  getPredefinisaniPregledi,
  pregledi,
  rezervisiPregled
}) => {
  const classes = useStyles();
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(10);
  const [orderBy, setOrderBy] = React.useState("datumPocetka");
  const [order, setOrder] = React.useState("asc");

  React.useEffect(() => {
    getPredefinisaniPregledi(klinikaId);
    //eslint-disable-next-line
  }, []);

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = event => {
    setRowsPerPage(+event.target.value);
    setPage(0);
  };

  const sort = sviPregledi => {
    return sviPregledi.concat().sort((a, b) => {
      if (orderBy === "tip") {
        return order === "asc"
          ? a.tipPregleda.naziv - b.tipPregleda.naziv
          : b.tipPregleda.naziv - a.tipPregleda.naziv;
      } else {
        return order === "asc"
          ? new Date(a.datumPocetka) - new Date(b.datumPocetka)
          : new Date(b.datumPocetka) - new Date(a.datumPocetka);
      }
    });
  };

  const handleRequestSort = (property, event) => {
    const isDesc = orderBy === property && order === "desc";
    setOrder(isDesc ? "asc" : "desc");
    setOrderBy(property);
  };

  const handleRequest = (id, version, e) => {
    rezervisiPregled(id, version);
  };

  return (
    <>
      <Typography
        variant="h4"
        component="h2"
        style={{ textAlign: "center", marginBottom: 30 }}
      >
        Predstojeci pregledi
      </Typography>
      <Paper className={classes.root}>
        {pregledi && (
          <>
            <div className={classes.tableWrapper}>
              <Table stickyHeader aria-label="sticky table">
                <TableHead>
                  <TableRow>
                    <TableCell align="left">Lekar</TableCell>
                    <TableCell align="right">
                      <TableSortLabel
                        active={orderBy === "tip"}
                        direction={order}
                        onClick={() => handleRequestSort("tip")}
                      >
                        Tip pregleda
                      </TableSortLabel>
                    </TableCell>
                    <TableCell align="right">
                      <TableSortLabel
                        active={orderBy === "datumPocetka"}
                        direction={order}
                        onClick={() => handleRequestSort("datumPocetka")}
                      >
                        Datum Pregleda
                      </TableSortLabel>
                    </TableCell>
                    <TableCell align="right">Cena</TableCell>
                    <TableCell align="right">Datum Zakazivanja</TableCell>
                    <TableCell align="right">Zakazi Pregled</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {pregledi && pregledi.length > 0 ? (
                    sort(pregledi)
                      .slice(
                        page * rowsPerPage,
                        page * rowsPerPage + rowsPerPage
                      )
                      .filter(p => new Date() < new Date(p.datumPocetka))
                      .map(row => {
                        return (
                          <TableRow hover key={row.id}>
                            <TableCell align="left">
                              {row.lekar.korisnik.ime +
                                " " +
                                row.lekar.korisnik.prezime}
                            </TableCell>
                            <TableCell align="right">
                              {row.tipPregleda.naziv}
                            </TableCell>
                            <TableCell align="right">
                              {row.datumPocetka}
                            </TableCell>
                            <TableCell align="right">
                              {row.tipPregleda.cenaPregleda}
                            </TableCell>
                            <TableCell align="right">
                              {row.datumKreiranja}
                            </TableCell>
                            <TableCell align="right">
                              <Tooltip
                                title={
                                  new Date() - new Date(row.datumPocetka) >
                                  -86400000
                                    ? "Otkazivanje nije moguce u periodu od 24 sata pre pregleda"
                                    : "Otkaži zakazani pregled"
                                }
                                leaveDelay={200}
                              >
                                <span>
                                  <Button
                                    color="primary"
                                    variant="outlined"
                                    onClick={() =>
                                      handleRequest(row.id, row.version)
                                    }
                                  >
                                    Zakazi
                                  </Button>
                                </span>
                              </Tooltip>
                            </TableCell>
                          </TableRow>
                        );
                      })
                  ) : (
                    <>
                      <TableRow hover tabIndex={-1}>
                        <TableCell>Prazno</TableCell>
                        <TableCell></TableCell>
                      </TableRow>
                    </>
                  )}
                </TableBody>
              </Table>
            </div>
            <TablePagination
              rowsPerPageOptions={[10, 25, 100]}
              component="div"
              count={
                pregledi
                  .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                  .filter(p => new Date() < new Date(p.datumPocetka)).length
              }
              rowsPerPage={rowsPerPage}
              page={page}
              backIconButtonProps={{
                "aria-label": "previous page"
              }}
              nextIconButtonProps={{
                "aria-label": "next page"
              }}
              onChangePage={handleChangePage}
              onChangeRowsPerPage={handleChangeRowsPerPage}
            />
          </>
        )}
      </Paper>
    </>
  );
};

const mapStateToProps = state => ({
  pregledi: state.pregled.listaPredefinisanih
});

export default connect(mapStateToProps, {
  getPredefinisaniPregledi,
  rezervisiPregled
})(UnapredDefinisaniPregledi);
