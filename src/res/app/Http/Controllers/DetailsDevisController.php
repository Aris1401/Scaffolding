<?php

namespace App\Http\Controllers;

use App\Models\DetailsDevis;
use Illuminate\Http\Request;

class DetailsDevisController extends Controller
{
    public function index() {
        $details_deviss = DetailsDevis::paginate(10);

        return view("crud.details_deviss.index", ["details_deviss" => $details_deviss]);
    }

    public function create() {
        return view("crud.details_deviss.create");
    }

    public function store(Request $request) {
        $details_devis = DetailsDevis::create([

            "dd_id_devis" => $request->input("dd_id_devis")
            "dd_id_type_travaux" => $request->input("dd_id_type_travaux")
            "dd_designation" => $request->input("dd_designation")
            "dd_code_details" => $request->input("dd_code_details")
            "dd_id_unite" => $request->input("dd_id_unite")
            "dd_quantite" => $request->input("dd_quantite")
            "dd_prix_unitaire" => $request->input("dd_prix_unitaire")
            "dd_montant_total" => $request->input("dd_montant_total")
            "dd_parent" => $request->input("dd_parent")
            "dd_description" => $request->input("dd_description")
        ]);

        return redirect()->route("details_deviss.index")->with("success","DetailsDevis inserer.");
    }

    public function edit($id) {
        $details_devis = DetailsDevis::find($id);

        return view("crud.details_deviss.edit", ["details_devis"=> $details_devis]);
    }

    public function update(Request $request, $id) {
        $details_devis = DetailsDevis::find($id);
        $details_devis->update([

            "dd_id_devis"=> $request->input("dd_id_devis")
            "dd_id_type_travaux"=> $request->input("dd_id_type_travaux")
            "dd_designation"=> $request->input("dd_designation")
            "dd_code_details"=> $request->input("dd_code_details")
            "dd_id_unite"=> $request->input("dd_id_unite")
            "dd_quantite"=> $request->input("dd_quantite")
            "dd_prix_unitaire"=> $request->input("dd_prix_unitaire")
            "dd_montant_total"=> $request->input("dd_montant_total")
            "dd_parent"=> $request->input("dd_parent")
            "dd_description"=> $request->input("dd_description")
        ]);

        return redirect()->route("details_deviss.index")->with("success","DetailsDevis mis ajour");
    }

    public function destroy($id) {
        $details_devis = DetailsDevis::find($id);
        $details_devis->delete();

        return redirect()->route("details_deviss.index")->with("success","DetailsDevis supprimer");
    }
}
