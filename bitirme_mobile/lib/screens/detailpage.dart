import 'package:flutter/material.dart';

class DetailPage extends StatefulWidget {
  @override
  _DetailPageState createState() => _DetailPageState();
}

class _DetailPageState extends State<DetailPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          "İlan Detayları",
          textAlign: TextAlign.center,
        ),
        actions: <Widget>[
          IconButton(
            icon: Icon(Icons.star),
            onPressed: () {},
            tooltip: "Favorilere ekle",
          ),
          IconButton(
            icon: Icon(Icons.share),
            onPressed: () {},
            tooltip: "Paylaş",
          ),
        ],
      ),
      body: Padding(
        padding: EdgeInsets.fromLTRB(0, 0, 0, 0),
        child: ListView(
          children: <Widget>[
            // SizedBox(height: 40),
            Container(
                margin: EdgeInsets.only(top: 10),
                height: MediaQuery.of(context).size.height / 2.0,
                width: MediaQuery.of(context).size.width,
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: <Widget>[
                    Text(
                      "84.000 KM'DE EXECUTIVE PASSAT HATCHBACK HATASIZ.",
                      style:
                          TextStyle(fontSize: 15, fontWeight: FontWeight.bold),
                      textAlign: TextAlign.center,
                    ),
                    Image.asset("assets/trend-10.jpg"),
                    FlatButton(
                      child: Text(
                        "Mustafa Bilbaşar",
                        textAlign: TextAlign.center,
                        style: TextStyle(fontSize: 23),
                      ),
                      onPressed: () => debugPrint("null"),
                    ),
                    Divider(
                      color: Colors.black38,
                      height: 10,
                      indent: 5,
                    ),
                  ],
                )),
            Container(
              padding: EdgeInsets.all(5),
              margin: EdgeInsets.only(top: 15),
              height: MediaQuery.of(context).size.height / 2,
              width: MediaQuery.of(context).size.width,
              child: Column(
                //mainAxisAlignment: MainAxisAlignment.spaceBetween,
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  Text("İlan Bilgileri",
                      style:
                          TextStyle(fontSize: 23, fontWeight: FontWeight.bold)),
                  Divider(
                    color: Colors.blue[300],
                    height: 10,
                    indent: 5,
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: <Widget>[
                      Container(
                        margin: EdgeInsets.fromLTRB(5, 5, 5, 5),
                        child: Text(
                          "Fiyat",
                          style: TextStyle(fontSize: 18),
                        ),
                      ),
                      Container(
                        margin: EdgeInsets.fromLTRB(5, 5, 5, 5),
                        child: Text(
                          "130.000₺",
                          style: TextStyle(fontSize: 18),
                        ),
                      )
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: <Widget>[
                      Container(
                        margin: EdgeInsets.fromLTRB(5, 5, 5, 5),
                        child: Text(
                          "İlan Tarihi",
                          style: TextStyle(fontSize: 18),
                        ),
                      ),
                      Container(
                        margin: EdgeInsets.fromLTRB(5, 5, 5, 5),
                        child: Text(
                          "12.04.2020",
                          style: TextStyle(fontSize: 18),
                        ),
                      )
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: <Widget>[
                      Container(
                        margin: EdgeInsets.fromLTRB(5, 5, 5, 5),
                        child: Text(
                          "İl/İlçe",
                          style: TextStyle(fontSize: 18),
                        ),
                      ),
                      Container(
                        margin: EdgeInsets.fromLTRB(5, 5, 5, 5),
                        child: Text(
                          "İstanbul/Başakşehir",
                          style: TextStyle(fontSize: 18),
                        ),
                      )
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: <Widget>[
                      Container(
                        margin: EdgeInsets.fromLTRB(5, 5, 5, 5),
                        child: Text(
                          "Kategori",
                          style: TextStyle(fontSize: 18),
                        ),
                      ),
                      Container(
                        margin: EdgeInsets.fromLTRB(5, 5, 5, 5),
                        child: Text(
                          "Araba",
                          style: TextStyle(fontSize: 18),
                        ),
                      )
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceAround,
                    children: <Widget>[
                      RaisedButton(
                          child: Text("Mesaj Gönder"),
                          color: Colors.greenAccent,
                          onPressed: () => debugPrint("null")),
                      RaisedButton(
                        child: Text("İlan Sahibi"),
                        color: Colors.red.shade900,
                        onPressed: () => debugPrint("null"),
                      ),
                      RaisedButton(
                        child: Text("Teklif Ver"),
                        color: Colors.lightBlue.shade300,
                        onPressed: () => debugPrint("null"),
                      ),
                    ],
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
