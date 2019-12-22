import React, { useEffect } from "react";

import { makeStyles } from "@material-ui/core/styles";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableHead from "@material-ui/core/TableHead";
import TablePagination from "@material-ui/core/TablePagination";
import TableRow from "@material-ui/core/TableRow";
import TableSortLabel from "@material-ui/core/TableSortLabel";

import Paper from "@material-ui/core/Paper";
import { connect } from "react-redux";
import { withRouter } from "react-router-dom";
import { getLekariKlinike } from "../../../store/actions/lekar";
import Dijalog from "../../Tabs/BrzoZakazivanjePregleda/Dijalog";

const useStyles = makeStyles(theme => ({
  root: {
    width: "100%",
    marginTop: theme.spacing(3)
  },
  paper: {
    width: "100%",
    marginBottom: theme.spacing(2)
  },
  table: {
    minWidth: 750
  },
  tableWrapper: {
    overflowX: "auto"
  },
  visuallyHidden: {
    border: 0,
    clip: "rect(0 0 0 0)",
    height: 1,
    margin: -1,
    overflow: "hidden",
    padding: 0,
    position: "absolute",
    top: 20,
    width: 1
  },
  formControl: {
    minWidth: 120
  }
}));

const PretragaSlobodniLekara = ({
  stariState,
  getLekariKlinike,
  idKlinike,
  lekari
}) => {
  useEffect(() => {
    getLekariKlinike(idKlinike, stariState);
    //eslint-disable-next-line
  }, []);
  const classes = useStyles();

  const [order, setOrder] = React.useState("asc");
  const [orderBy, setOrderBy] = React.useState("ime");

  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(5);

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = event => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  const sort = lekari => {
    return lekari.sort((a, b) => {
      if (orderBy === "ime") {
        return order === "asc"
          ? a.korisnik.ime < b.korisnik.ime
            ? 1
            : -1
          : b.korisnik.ime < a.korisnik.ime
          ? 1
          : -1;
      } else if (orderBy === "ocena") {
        return order === "asc" ? a.ocena - b.ocena : b.ocena - a.ocena;
      } else {
        return order === "asc"
          ? a.korisnik.prezime < b.korisnik.prezime
            ? 1
            : -1
          : b.korisnik.prezime < a.korisnik.prezime
          ? 1
          : -1;
      }
    });
  };

  const handleRequestSort = (property, event) => {
    const isDesc = orderBy === property && order === "desc";
    setOrder(isDesc ? "asc" : "desc");
    setOrderBy(property);
  };

  return (
    <div className={classes.root}>
      {lekari && (
        <Paper className={classes.paper}>
          <div className={classes.tableWrapper}>
            <Table
              className={classes.table}
              aria-labelledby="tableTitle"
              aria-label="enhanced table"
            >
              <TableHead>
                <TableRow>
                  <TableCell align="left">
                    <TableSortLabel
                      active={orderBy === "ime"}
                      direction={order}
                      onClick={() => handleRequestSort("ime")}
                    >
                      Ime
                    </TableSortLabel>
                  </TableCell>
                  <TableCell align="left">
                    <TableSortLabel
                      active={orderBy === "prezime"}
                      direction={order}
                      onClick={() => handleRequestSort("prezime")}
                    >
                      Prezime
                    </TableSortLabel>
                  </TableCell>
                  <TableCell align="left">
                    <TableSortLabel
                      active={orderBy === "ocena"}
                      direction={order}
                      onClick={() => handleRequestSort("ocena")}
                    >
                      Ocena
                    </TableSortLabel>
                  </TableCell>

                  <TableCell align="right">Termini Pregleda</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {lekari &&
                  lekari.length > 0 &&
                  sort(lekari)
                    .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                    .map((row, index) => {
                      return (
                        <TableRow
                          hover
                          role="checkbox"
                          tabIndex={-1}
                          key={row.id}
                        >
                          <TableCell component="th" allign="left">
                            {row.korisnik.ime}
                          </TableCell>
                          <TableCell align="left">
                            {row.korisnik.prezime}
                          </TableCell>
                          <TableCell align="left">{row.ocena}</TableCell>

                          <TableCell align="right">
                            <Dijalog
                              id={row.id}
                              datum={!stariState.datum ? "" : stariState.datum}
                              lekar={row}
                            />
                          </TableCell>
                        </TableRow>
                      );
                    })}
                {rowsPerPage -
                  Math.min(rowsPerPage, lekari.length - page * rowsPerPage) >
                  0 && (
                  <TableRow
                    style={{
                      height:
                        53 *
                        (rowsPerPage -
                          Math.min(
                            rowsPerPage,
                            lekari.length - page * rowsPerPage
                          ))
                    }}
                  >
                    <TableCell colSpan={6} />
                  </TableRow>
                )}
              </TableBody>
            </Table>
          </div>
          <TablePagination
            rowsPerPageOptions={[5, 10, 25]}
            component="div"
            count={lekari.length}
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
        </Paper>
      )}
    </div>
  );
};

const mapStateToProps = state => ({
  lekari: state.lekar.lekarList
});

export default withRouter(
  connect(mapStateToProps, { getLekariKlinike })(PretragaSlobodniLekara)
);
