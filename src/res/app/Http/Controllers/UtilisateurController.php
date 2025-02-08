<?php

namespace App\Http\Controllers;

use App\Models\Utilisateur;
use Illuminate\Http\Request;

class UtilisateurController extends Controller
{
    public function index() {
        $utilisateurs = Utilisateur::paginate(10);

        return view("crud.utilisateurs.index", ["utilisateurs" => $utilisateurs]);
    }

    public function create() {
        return view("crud.utilisateurs.create");
    }

    public function store(Request $request) {
        $utilisateur = Utilisateur::create([

            "u_nom" => $request->input("u_nom")
            "u_prenom" => $request->input("u_prenom")
            "u_id_genre" => $request->input("u_id_genre")
            "u_date_de_naissance" => $request->input("u_date_de_naissance")
            "u_email" => $request->input("u_email")
            "u_contact" => $request->input("u_contact")
            "u_mot_de_passe" => $request->input("u_mot_de_passe")
            "u_id_profil_utilisateur" => $request->input("u_id_profil_utilisateur")
        ]);

        return redirect()->route("utilisateurs.index")->with("success","Utilisateur inserer.");
    }

    public function edit($id) {
        $utilisateur = Utilisateur::find($id);

        return view("crud.utilisateurs.edit", ["utilisateur"=> $utilisateur]);
    }

    public function update(Request $request, $id) {
        $utilisateur = Utilisateur::find($id);
        $utilisateur->update([

            "u_nom"=> $request->input("u_nom")
            "u_prenom"=> $request->input("u_prenom")
            "u_id_genre"=> $request->input("u_id_genre")
            "u_date_de_naissance"=> $request->input("u_date_de_naissance")
            "u_email"=> $request->input("u_email")
            "u_contact"=> $request->input("u_contact")
            "u_mot_de_passe"=> $request->input("u_mot_de_passe")
            "u_id_profil_utilisateur"=> $request->input("u_id_profil_utilisateur")
        ]);

        return redirect()->route("utilisateurs.index")->with("success","Utilisateur mis ajour");
    }

    public function destroy($id) {
        $utilisateur = Utilisateur::find($id);
        $utilisateur->delete();

        return redirect()->route("utilisateurs.index")->with("success","Utilisateur supprimer");
    }
}
