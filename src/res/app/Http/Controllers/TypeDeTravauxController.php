<?php

namespace App\Http\Controllers;

use App\Models\TypeDeTravaux;
use Illuminate\Http\Request;

class TypeDeTravauxController extends Controller
{
    public function index() {
        $type_de_travauxs = TypeDeTravaux::paginate(10);

        return view("crud.type_de_travauxs.index", ["type_de_travauxs" => $type_de_travauxs]);
    }

    public function create() {
        return view("crud.type_de_travauxs.create");
    }

    public function store(Request $request) {
        $type_de_travaux = TypeDeTravaux::create([

            "tt_designation" => $request->input("tt_designation")
        ]);

        return redirect()->route("type_de_travauxs.index")->with("success","TypeDeTravaux inserer.");
    }

    public function edit($id) {
        $type_de_travaux = TypeDeTravaux::find($id);

        return view("crud.type_de_travauxs.edit", ["type_de_travaux"=> $type_de_travaux]);
    }

    public function update(Request $request, $id) {
        $type_de_travaux = TypeDeTravaux::find($id);
        $type_de_travaux->update([

            "tt_designation"=> $request->input("tt_designation")
        ]);

        return redirect()->route("type_de_travauxs.index")->with("success","TypeDeTravaux mis ajour");
    }

    public function destroy($id) {
        $type_de_travaux = TypeDeTravaux::find($id);
        $type_de_travaux->delete();

        return redirect()->route("type_de_travauxs.index")->with("success","TypeDeTravaux supprimer");
    }
}
