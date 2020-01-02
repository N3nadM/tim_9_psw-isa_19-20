import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import Paper from "@material-ui/core/Paper";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableHead from "@material-ui/core/TableHead";
import TablePagination from "@material-ui/core/TablePagination";
import TableRow from "@material-ui/core/TableRow";
import CheckCircleOutlineIcon from "@material-ui/icons/CheckCircleOutline";
import CancelIcon from "@material-ui/icons/Cancel";

const useStyles = makeStyles({
  root: {
    width: "100%"
  },
  tableWrapper: {
    maxHeight: 440,
    overflow: "auto"
  }
});

export default function TabelaRecept({ items }) {
  const classes = useStyles();
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(10);

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = event => {
    setRowsPerPage(+event.target.value);
    setPage(0);
  };

  console.log(items);

  return (
    <>
      <Paper className={classes.root}>
        <div className={classes.tableWrapper}>
          <Table stickyHeader aria-label="sticky table">
            <TableHead>
              <TableRow>
                <TableCell>Naziv</TableCell>
                <TableCell align="right">Datum isticanja</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {items && items.length > 0 ? (
                items
                  .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                  .map(row => {
                    return (
                      <TableRow
                        hover
                        role="checkbox"
                        tabIndex={-1}
                        key={row.id}
                      >
                        <TableCell align="left">
                          {row.overen ? (
                            <CheckCircleOutlineIcon
                              style={{
                                position: "relative",
                                top: 5,
                                left: 0,
                                marginRight: 5,
                                color: "#4caf50"
                              }}
                            />
                          ) : (
                            <CancelIcon
                              style={{
                                position: "relative",
                                top: 7,
                                left: 0,
                                marginRight: 5,
                                color: "#f44336"
                              }}
                            />
                          )}
                          {row.lek.naziv}
                        </TableCell>
                        <TableCell align="right">
                          {row.datumIsticanja}
                        </TableCell>
                      </TableRow>
                    );
                  })
              ) : (
                <>
                  <TableCell>Prazno</TableCell>
                  <TableCell></TableCell>
                </>
              )}
            </TableBody>
          </Table>
        </div>
        <TablePagination
          rowsPerPageOptions={[10, 25, 100]}
          component="div"
          count={items.length}
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
      {items && items.length > 0 && (
        <Paper
          style={{
            marginTop: 20,
            display: "flex",
            justifyContent: "space-around"
          }}
        >
          <div>
            <CheckCircleOutlineIcon
              style={{
                position: "relative",
                top: 5,
                left: 0,
                marginRight: 5,
                color: "#4caf50"
              }}
            />{" "}
            Overen recept
          </div>
          <div>
            <CancelIcon
              style={{
                position: "relative",
                top: 7,
                left: 0,
                marginRight: 5,
                color: "#f44336"
              }}
            />{" "}
            Ne overen recept
          </div>
        </Paper>
      )}
    </>
  );
}
