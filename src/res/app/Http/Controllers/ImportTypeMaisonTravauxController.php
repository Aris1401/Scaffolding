<?php

namespace App\Http\Controllers;

use App\Models\ImportTypeMaisonTravaux;
use Illuminate\Http\Request;

class ImportTypeMaisonTravauxController extends Controller
{
    public function index() {
        $import_type_maison_travauxs = ImportTypeMaisonTravaux::paginate(10);

        return view("crud.import_type_maison_travauxs.index", ["import_type_maison_travauxs" => $import_type_maison_travauxs]);
    }

    public function create() {
        return view("crud.import_type_maison_travauxs.create");
    }

    public function store(Request $request) {
        $import_type_maison_travaux = ImportTypeMaisonTravaux::create([

            "type_maison" => $request->input("type_maison")
            "description" => $request->input("description")
            "surface" => $request->input("surface")
            "code_travaux" => $request->input("code_travaux")
            "type_travaux" => $request->input("type_travaux")
            "unite" => $request->input("unite")
            "prix_unitaire" => $request->input("prix_unitaire")
            "quantite" => $request->input("quantite")
            "duree_travaux" => $request->input("duree_travaux")
        ]);

        return redirect()->route("import_type_maison_travauxs.index")->with("success","ImportTypeMaisonTravaux inserer.");
    }

    public function edit($id) {
        $import_type_maison_travaux = ImportTypeMaisonTravaux::find($id);

        return view("crud.import_type_maison_travauxs.edit", ["import_type_maison_travaux"=> $import_type_maison_travaux]);
    }

    public function update(Request $request, $id) {
        $import_type_maison_travaux = ImportTypeMaisonTravaux::find($id);
        $import_type_maison_travaux->update([

            "type_maison"=> $request->input("type_maison")
            "description"=> $request->input("description")
            "surface"=> $request->input("surface")
            "code_travaux"=> $request->input("code_travaux")
            "type_travaux"=> $request->input("type_travaux")
            "unite"=> $request->input("unite")
            "prix_unitaire"=> $request->input("prix_unitaire")
            "quantite"=> $request->input("quantite")
            "duree_travaux"=> $request->input("duree_travaux")
        ]);

        return redirect()->route("import_type_maison_travauxs.index")->with("success","ImportTypeMaisonTravaux mis ajour");
    }

    public function destroy($id) {
        $import_type_maison_travaux = ImportTypeMaisonTravaux::find($id);
        $import_type_maison_travaux->delete();

        return redirect()->route("import_type_maison_travauxs.index")->with("success","ImportTypeMaisonTravaux supprimer");
    }
}
