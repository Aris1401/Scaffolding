<?php

namespace App\Http\Controllers;

use App\Models\ImportRealisation;
use Illuminate\Http\Request;

class ImportRealisationController extends Controller
{
    public function index() {
        $import_realisations = ImportRealisation::paginate(10);

        return view("crud.import_realisations.index", ["import_realisations" => $import_realisations]);
    }

    public function create() {
        return view("crud.import_realisations.create");
    }

    public function store(Request $request) {
        $import_realisation = ImportRealisation::create([

            "client" => $request->input("client")
            "ref_devis" => $request->input("ref_devis")
            "type_maison" => $request->input("type_maison")
            "finition" => $request->input("finition")
            "taux_finition" => $request->input("taux_finition")
            "date_devis" => $request->input("date_devis")
            "date_debut" => $request->input("date_debut")
            "lieu" => $request->input("lieu")
        ]);

        return redirect()->route("import_realisations.index")->with("success","ImportRealisation inserer.");
    }

    public function edit($id) {
        $import_realisation = ImportRealisation::find($id);

        return view("crud.import_realisations.edit", ["import_realisation"=> $import_realisation]);
    }

    public function update(Request $request, $id) {
        $import_realisation = ImportRealisation::find($id);
        $import_realisation->update([

            "client"=> $request->input("client")
            "ref_devis"=> $request->input("ref_devis")
            "type_maison"=> $request->input("type_maison")
            "finition"=> $request->input("finition")
            "taux_finition"=> $request->input("taux_finition")
            "date_devis"=> $request->input("date_devis")
            "date_debut"=> $request->input("date_debut")
            "lieu"=> $request->input("lieu")
        ]);

        return redirect()->route("import_realisations.index")->with("success","ImportRealisation mis ajour");
    }

    public function destroy($id) {
        $import_realisation = ImportRealisation::find($id);
        $import_realisation->delete();

        return redirect()->route("import_realisations.index")->with("success","ImportRealisation supprimer");
    }
}
