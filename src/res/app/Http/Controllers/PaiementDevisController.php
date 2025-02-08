<?php

namespace App\Http\Controllers;

use App\Models\PaiementDevis;
use Illuminate\Http\Request;

class PaiementDevisController extends Controller
{
    public function index() {
        $paiement_deviss = PaiementDevis::paginate(10);

        return view("crud.paiement_deviss.index", ["paiement_deviss" => $paiement_deviss]);
    }

    public function create() {
        return view("crud.paiement_deviss.create");
    }

    public function store(Request $request) {
        $paiement_devis = PaiementDevis::create([

            "pd_id_realisation_travaux" => $request->input("pd_id_realisation_travaux")
            "pd_date_de_paiement" => $request->input("pd_date_de_paiement")
            "pd_montant" => $request->input("pd_montant")
            "pd_ref_paiement" => $request->input("pd_ref_paiement")
        ]);

        return redirect()->route("paiement_deviss.index")->with("success","PaiementDevis inserer.");
    }

    public function edit($id) {
        $paiement_devis = PaiementDevis::find($id);

        return view("crud.paiement_deviss.edit", ["paiement_devis"=> $paiement_devis]);
    }

    public function update(Request $request, $id) {
        $paiement_devis = PaiementDevis::find($id);
        $paiement_devis->update([

            "pd_id_realisation_travaux"=> $request->input("pd_id_realisation_travaux")
            "pd_date_de_paiement"=> $request->input("pd_date_de_paiement")
            "pd_montant"=> $request->input("pd_montant")
            "pd_ref_paiement"=> $request->input("pd_ref_paiement")
        ]);

        return redirect()->route("paiement_deviss.index")->with("success","PaiementDevis mis ajour");
    }

    public function destroy($id) {
        $paiement_devis = PaiementDevis::find($id);
        $paiement_devis->delete();

        return redirect()->route("paiement_deviss.index")->with("success","PaiementDevis supprimer");
    }
}
