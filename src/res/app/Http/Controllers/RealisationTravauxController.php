<?php

namespace App\Http\Controllers;

use App\Models\RealisationTravaux;
use Illuminate\Http\Request;

class RealisationTravauxController extends Controller
{
    public function index() {
        $realisation_travauxs = RealisationTravaux::paginate(10);

        return view("crud.realisation_travauxs.index", ["realisation_travauxs" => $realisation_travauxs]);
    }

    public function create() {
        return view("crud.realisation_travauxs.create");
    }

    public function store(Request $request) {
        $realisation_travaux = RealisationTravaux::create([

            "rt_id_utilisateur" => $request->input("rt_id_utilisateur")
            "rt_id_devis" => $request->input("rt_id_devis")
            "rt_id_type_de_maison" => $request->input("rt_id_type_de_maison")
            "rt_id_type_de_finition" => $request->input("rt_id_type_de_finition")
            "rt_date_ajout_realisation" => $request->input("rt_date_ajout_realisation")
            "rt_date_debut_travaux" => $request->input("rt_date_debut_travaux")
            "rt_date_fin_travaux" => $request->input("rt_date_fin_travaux")
            "rt_montant_total" => $request->input("rt_montant_total")
            "rt_augmentation" => $request->input("rt_augmentation")
            "rt_duree_travail" => $request->input("rt_duree_travail")
            "rt_ref_devis" => $request->input("rt_ref_devis")
            "rt_lieu" => $request->input("rt_lieu")
        ]);

        return redirect()->route("realisation_travauxs.index")->with("success","RealisationTravaux inserer.");
    }

    public function edit($id) {
        $realisation_travaux = RealisationTravaux::find($id);

        return view("crud.realisation_travauxs.edit", ["realisation_travaux"=> $realisation_travaux]);
    }

    public function update(Request $request, $id) {
        $realisation_travaux = RealisationTravaux::find($id);
        $realisation_travaux->update([

            "rt_id_utilisateur"=> $request->input("rt_id_utilisateur")
            "rt_id_devis"=> $request->input("rt_id_devis")
            "rt_id_type_de_maison"=> $request->input("rt_id_type_de_maison")
            "rt_id_type_de_finition"=> $request->input("rt_id_type_de_finition")
            "rt_date_ajout_realisation"=> $request->input("rt_date_ajout_realisation")
            "rt_date_debut_travaux"=> $request->input("rt_date_debut_travaux")
            "rt_date_fin_travaux"=> $request->input("rt_date_fin_travaux")
            "rt_montant_total"=> $request->input("rt_montant_total")
            "rt_augmentation"=> $request->input("rt_augmentation")
            "rt_duree_travail"=> $request->input("rt_duree_travail")
            "rt_ref_devis"=> $request->input("rt_ref_devis")
            "rt_lieu"=> $request->input("rt_lieu")
        ]);

        return redirect()->route("realisation_travauxs.index")->with("success","RealisationTravaux mis ajour");
    }

    public function destroy($id) {
        $realisation_travaux = RealisationTravaux::find($id);
        $realisation_travaux->delete();

        return redirect()->route("realisation_travauxs.index")->with("success","RealisationTravaux supprimer");
    }
}
