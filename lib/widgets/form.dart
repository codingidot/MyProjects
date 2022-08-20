import 'package:flutter/material.dart';

class AppForm extends StatefulWidget {

 GlobalKey<FormState>? formKey = GlobalKey<FormState>();

 TextEditingController? idController;
 TextEditingController? pwController;

 AppForm({this.formKey, this.idController,this.pwController});

  @override
  _AppFormState createState() => _AppFormState();
}

class _AppFormState extends State<AppForm> {
  @override
  Widget build(BuildContext context) {
    return Form(
      key: widget.formKey,
      child: Column(
        children: <Widget>[
          TextFormField(
            controller: widget.idController,
            keyboardType: TextInputType.text,
            decoration: InputDecoration(label: Text('Id')),
          ),
          TextFormField(
            controller: widget.pwController,
            keyboardType: TextInputType.text,
            decoration: InputDecoration(label: Text('Pw')),
          ),
        ],
      ),
    );
  }
}
