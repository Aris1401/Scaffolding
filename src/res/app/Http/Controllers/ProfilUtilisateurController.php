<?php

namespace App\Http\Controllers;

use App\Models\ProfilUtilisateur;
use Illuminate\Http\Request;

class ProfilUtilisateurController extends Controller
{
    public function index() {
        $profil_utilisateurs = ProfilUtilisateur::paginate(10);

        return view("crud.profil_utilisateurs.index", ["profil_utilisateurs" => $profil_utilisateurs]);
    }

    public function create() {
        return view("crud.profil_utilisateurs.create");
    }

    public function store(Request $request) {
        $profil_utilisateur = ProfilUtilisateur::create([

            "pu_designation" => $request->input("pu_designation")
        ]);

        return redirect()->route("profil_utilisateurs.index")->with("success","ProfilUtilisateur inserer.");
    }

    public function edit($id) {
        $profil_utilisateur = ProfilUtilisateur::find($id);

        return view("crud.profil_utilisateurs.edit", ["profil_utilisateur"=> $profil_utilisateur]);
    }

    public function update(Request $request, $id) {
        $profil_utilisateur = ProfilUtilisateur::find($id);
        $profil_utilisateur->update([

            "pu_designation"=> $request->input("pu_designation")
        ]);

        return redirect()->route("profil_utilisateurs.index")->with("success","ProfilUtilisateur mis ajour");
    }

    public function destroy($id) {
        $profil_utilisateur = ProfilUtilisateur::find($id);
        $profil_utilisateur->delete();

        return redirect()->route("profil_utilisateurs.index")->with("success","ProfilUtilisateur supprimer");
    }
}
