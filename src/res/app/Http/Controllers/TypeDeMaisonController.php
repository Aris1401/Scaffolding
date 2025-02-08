<?php

namespace App\Http\Controllers;

use App\Models\TypeDeMaison;
use Illuminate\Http\Request;

class TypeDeMaisonController extends Controller
{
    public function index() {
        $type_de_maisons = TypeDeMaison::paginate(10);

        return view("crud.type_de_maisons.index", ["type_de_maisons" => $type_de_maisons]);
    }

    public function create() {
        return view("crud.type_de_maisons.create");
    }

    public function store(Request $request) {
        $type_de_maison = TypeDeMaison::create([

            "tm_designation" => $request->input("tm_designation")
            "tm_description" => $request->input("tm_description")
            "tm_duree_travaux" => $request->input("tm_duree_travaux")
            "tm_surface" => $request->input("tm_surface")
        ]);

        return redirect()->route("type_de_maisons.index")->with("success","TypeDeMaison inserer.");
    }

    public function edit($id) {
        $type_de_maison = TypeDeMaison::find($id);

        return view("crud.type_de_maisons.edit", ["type_de_maison"=> $type_de_maison]);
    }

    public function update(Request $request, $id) {
        $type_de_maison = TypeDeMaison::find($id);
        $type_de_maison->update([

            "tm_designation"=> $request->input("tm_designation")
            "tm_description"=> $request->input("tm_description")
            "tm_duree_travaux"=> $request->input("tm_duree_travaux")
            "tm_surface"=> $request->input("tm_surface")
        ]);

        return redirect()->route("type_de_maisons.index")->with("success","TypeDeMaison mis ajour");
    }

    public function destroy($id) {
        $type_de_maison = TypeDeMaison::find($id);
        $type_de_maison->delete();

        return redirect()->route("type_de_maisons.index")->with("success","TypeDeMaison supprimer");
    }
}
