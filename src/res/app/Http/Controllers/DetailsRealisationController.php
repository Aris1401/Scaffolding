<?php

namespace App\Http\Controllers;

use App\Models\DetailsRealisation;
use Illuminate\Http\Request;

class DetailsRealisationController extends Controller
{
    public function index() {
        $details_realisations = DetailsRealisation::paginate(10);

        return view("crud.details_realisations.index", ["details_realisations" => $details_realisations]);
    }

    public function create() {
        return view("crud.details_realisations.create");
    }

    public function store(Request $request) {
        $details_realisation = DetailsRealisation::create([

            "dr_id_realisation_travaux" => $request->input("dr_id_realisation_travaux")
            "dr_id_type_travaux" => $request->input("dr_id_type_travaux")
            "dr_designation" => $request->input("dr_designation")
            "dr_code_details" => $request->input("dr_code_details")
            "dr_id_unite" => $request->input("dr_id_unite")
            "dr_quantite" => $request->input("dr_quantite")
            "dr_prix_unitaire" => $request->input("dr_prix_unitaire")
            "dr_montant_total" => $request->input("dr_montant_total")
            "dr_parent" => $request->input("dr_parent")
            "dr_description" => $request->input("dr_description")
        ]);

        return redirect()->route("details_realisations.index")->with("success","DetailsRealisation inserer.");
    }

    public function edit($id) {
        $details_realisation = DetailsRealisation::find($id);

        return view("crud.details_realisations.edit", ["details_realisation"=> $details_realisation]);
    }

    public function update(Request $request, $id) {
        $details_realisation = DetailsRealisation::find($id);
        $details_realisation->update([

            "dr_id_realisation_travaux"=> $request->input("dr_id_realisation_travaux")
            "dr_id_type_travaux"=> $request->input("dr_id_type_travaux")
            "dr_designation"=> $request->input("dr_designation")
            "dr_code_details"=> $request->input("dr_code_details")
            "dr_id_unite"=> $request->input("dr_id_unite")
            "dr_quantite"=> $request->input("dr_quantite")
            "dr_prix_unitaire"=> $request->input("dr_prix_unitaire")
            "dr_montant_total"=> $request->input("dr_montant_total")
            "dr_parent"=> $request->input("dr_parent")
            "dr_description"=> $request->input("dr_description")
        ]);

        return redirect()->route("details_realisations.index")->with("success","DetailsRealisation mis ajour");
    }

    public function destroy($id) {
        $details_realisation = DetailsRealisation::find($id);
        $details_realisation->delete();

        return redirect()->route("details_realisations.index")->with("success","DetailsRealisation supprimer");
    }
}
