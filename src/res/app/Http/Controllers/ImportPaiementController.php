<?php

namespace App\Http\Controllers;

use App\Models\ImportPaiement;
use Illuminate\Http\Request;

class ImportPaiementController extends Controller
{
    public function index() {
        $import_paiements = ImportPaiement::paginate(10);

        return view("crud.import_paiements.index", ["import_paiements" => $import_paiements]);
    }

    public function create() {
        return view("crud.import_paiements.create");
    }

    public function store(Request $request) {
        $import_paiement = ImportPaiement::create([

            "ref_devis" => $request->input("ref_devis")
            "ref_paiement" => $request->input("ref_paiement")
            "date_paiement" => $request->input("date_paiement")
            "montant" => $request->input("montant")
        ]);

        return redirect()->route("import_paiements.index")->with("success","ImportPaiement inserer.");
    }

    public function edit($id) {
        $import_paiement = ImportPaiement::find($id);

        return view("crud.import_paiements.edit", ["import_paiement"=> $import_paiement]);
    }

    public function update(Request $request, $id) {
        $import_paiement = ImportPaiement::find($id);
        $import_paiement->update([

            "ref_devis"=> $request->input("ref_devis")
            "ref_paiement"=> $request->input("ref_paiement")
            "date_paiement"=> $request->input("date_paiement")
            "montant"=> $request->input("montant")
        ]);

        return redirect()->route("import_paiements.index")->with("success","ImportPaiement mis ajour");
    }

    public function destroy($id) {
        $import_paiement = ImportPaiement::find($id);
        $import_paiement->delete();

        return redirect()->route("import_paiements.index")->with("success","ImportPaiement supprimer");
    }
}
