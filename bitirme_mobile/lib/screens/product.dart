import 'dart:io';

import 'package:flutter/material.dart';
import 'package:image_picker_modern/image_picker_modern.dart';

class AddProduct extends StatefulWidget {
  @override
  _AddProductState createState() => _AddProductState();
}

class _AddProductState extends State<AddProduct> {
  List<String> _category = ['Araba', 'Bilgisayar', 'Hizmet', 'Telefon']; // Option 2
  String _selectedLocation;
  String _selectedLocation2; // Option 2
  List<String> _altkategori = ['Android', 'IOS', 'Windows Phone'];

  File _image;

  Future getImage() async {
    var image = await ImagePicker.pickImage(source: ImageSource.gallery);

    setState(() {
      _image = image;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text(
            "İlan ver",
            style: TextStyle(fontSize: 25, color: Colors.blue.shade400),
          ),
          backgroundColor: Colors.white,
        ),
        body: Container(
          child: Padding(
            padding: EdgeInsets.fromLTRB(10, 20, 10, 10),
            child: ListView(
              children: <Widget>[
                Column(
                  children: <Widget>[
                    TextFormField(
                      decoration: new InputDecoration(
                        hintText: 'Ürün başlığı girin..',
                        border: OutlineInputBorder(
                            borderRadius: BorderRadius.circular(10.0),
                            borderSide: BorderSide()),
                      ),
                    ),
                    SizedBox(
                      height: 10.0,
                    ),
                    TextFormField(
                      decoration: new InputDecoration(
                        hintText: 'Açıklama başlığı girin..',
                        border: OutlineInputBorder(
                            borderRadius: BorderRadius.circular(10.0),
                            borderSide: BorderSide()),
                      ),
                    ),
                    SizedBox(
                      height: 10.0,
                    ),
                    Container(
                      width: 400,
                      decoration: ShapeDecoration(
                        shape: RoundedRectangleBorder(
                          side: BorderSide(
                            width: 0.6,
                          ),
                          borderRadius: BorderRadius.all(Radius.circular(10.0)),
                        ),
                      ),
                      child: DropdownButton(
                        isExpanded: true,
                        
                        
                        hint: Text(
                          'Lütfen kategori seçiniz.',
                          style: TextStyle(fontSize: 20),
                        ), // Not necessary for Option 1
                        value: _selectedLocation,
                        onChanged: (newValue) {
                          setState(() {
                            _selectedLocation = newValue;
                          });
                        },
                        items: _category.map((String location) {
                          return DropdownMenuItem(
                            child: new Text(location),
                            value: location,
                          );
                        }).toList(),
                        
                        
                      ),
                    ),
                    SizedBox(height: 10),
                    Container(
                      width: 400,
                      decoration: ShapeDecoration(
                        shape: RoundedRectangleBorder(
                          side: BorderSide(
                            width: 0.6,
                          ),
                          borderRadius: BorderRadius.all(Radius.circular(10.0)),
                        ),
                      ),
                      child: DropdownButton(
                        isExpanded: true,
                        hint: Text(
                          'Lütfen alt kategori seçiniz.',
                          style: TextStyle(fontSize: 20),
                        ), // Not necessary for Option 1
                        value: _selectedLocation2,
                        onChanged: (newValue) {
                          setState(() {
                            _selectedLocation2 = newValue;
                          });
                        },
                        items: _altkategori.map((location) {
                          return DropdownMenuItem(
                            child: new Text(location),
                            value: location,
                          );
                        }).toList(),
                      ),
                    ),
                    SizedBox(height: 15),
                    Row(
                      children: <Widget>[
                        Expanded(
                            flex: 3,
                            child: Container(
                              decoration: BoxDecoration(
                                  border: Border.all(width: 0.5),
                                  borderRadius:
                                      BorderRadius.all(Radius.circular(10))),
                              child: _image == null
                                  ? Text(
                                      'Fotoğraf seçiniz.',
                                      style: TextStyle(fontSize: 30),
                                    )
                                  : Text("fotograf yüklendi."),
                            )),
                        Expanded(
                            flex: 1,
                            child: RaisedButton(
                                child: Text("Browse"),
                                color: Colors.blue.shade400,
                                onPressed: getImage))
                      ],
                    ),
                    SizedBox(height: 35),
                    Container(
                      alignment: Alignment.center,
                      child: RaisedButton(
                          child: Text("Yükle",style: TextStyle(fontSize: 25),),
                          color: Colors.green.shade400,
                          onPressed: () => {}),
                    ),
                  ],
                ),
              ],
            ),
          ),
        ));
  }
}
