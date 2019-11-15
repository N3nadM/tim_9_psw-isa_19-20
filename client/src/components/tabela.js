import React, { useEffect, useState } from "react";
import Button from "@material-ui/core/Button";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import Axios from "axios";

const Tabela = () => {
  const [zahtevi, setZahtevi] = useState([]);

  useEffect(() => {
    (async () => {
      const response = await Axios.get(
        "http://localhost:8080/api/adminkc/zahtevi"
      );

      setZahtevi([...response.data]);
    })();
  }, []);

  return (
    <div className="Tabela">
      <Table aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>
              <b>Ime</b>
            </TableCell>
            <TableCell>
              <b>Prezime</b>
            </TableCell>
            <TableCell>
              <b>Username</b>
            </TableCell>
            <TableCell>
              <b>E-mail</b>
            </TableCell>
            <TableCell>
              <b>Adresa</b>
            </TableCell>
            <TableCell>
              <b>JBZO</b>
            </TableCell>
            <TableCell></TableCell>
            <TableCell></TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {zahtevi.map((zahtev, i) => (
            <TableRow key={zahtev.id}>
              <TableCell>{zahtev.ime}</TableCell>
              <TableCell>{zahtev.prezime}</TableCell>
              <TableCell>{zahtev.username}</TableCell>
              <TableCell>{zahtev.email}</TableCell>
              <TableCell>{zahtev.adresa}</TableCell>
              <TableCell>{zahtev.jbzo}</TableCell>
              <TableCell>
                <Button
                  variant="contained"
                  color="primary"
                  onClick={async () => {
                    await Axios.post(
                      `http://localhost:8080/api/users/register/${zahtev.email}`
                    );
                    setZahtevi(zahtevi.filter(z => z.email !== zahtev.email));
                  }}
                >
                  Prihvati
                </Button>
              </TableCell>
              <TableCell>
                <Button
                  variant="contained"
                  color="secundary"
                  onClick={async () =>
                    await Axios.post(
                      `http://localhost:8080/api/users/denie/${zahtev.email}`,
                      { message: "denied request" }
                    )
                  }
                >
                  Odbij
                </Button>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
  );
};

export default Tabela;
