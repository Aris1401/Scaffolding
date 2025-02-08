<?php

namespace App\Http\Controllers;

use App\Models\Devis;
use Illuminate\Http\Request;

class DevisController extends Controller
{
    public function index() {
        $deviss = Devis::paginate(10);

        return view("crud.deviss.index", ["deviss" => $deviss]);
    }

    public function create() {
        return view("crud.deviss.create");
    }

    public function store(Request $request) {
        $devis = Devis::create([

            "d_date_ajout" => $request->input("d_date_ajout")
            "d_type_de_maison" => $request->input("d_type_de_maison")
            "d_designation" => $request->input("d_designation")
            "d_montant_total" => $request->input("d_montant_total")
        ]);

        return redirect()->route("deviss.index")->with("success","Devis inserer.");
    }

    public function edit($id) {
        $devis = Devis::find($id);

        return view("crud.deviss.edit", ["devis"=> $devis]);
    }

    public function update(Request $request, $id) {
        $devis = Devis::find($id);
        $devis->update([

            "d_date_ajout"=> $request->input("d_date_ajout")
            "d_type_de_maison"=> $request->input("d_type_de_maison")
            "d_designation"=> $request->input("d_designation")
            "d_montant_total"=> $request->input("d_montant_total")
        ]);

        return redirect()->route("deviss.index")->with("success","Devis mis ajour");
    }

    public function destroy($id) {
        $devis = Devis::find($id);
        $devis->delete();

        return redirect()->route("deviss.index")->with("success","Devis supprimer");
    }
}
