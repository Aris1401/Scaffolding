<?php

namespace App\Http\Controllers;

use App\Models\Mois;
use Illuminate\Http\Request;

class MoisController extends Controller
{
    public function index() {
        $moiss = Mois::paginate(10);

        return view("crud.moiss.index", ["moiss" => $moiss]);
    }

    public function create() {
        return view("crud.moiss.create");
    }

    public function store(Request $request) {
        $mois = Mois::create([

            "m_designation" => $request->input("m_designation")
            "m_position" => $request->input("m_position")
        ]);

        return redirect()->route("moiss.index")->with("success","Mois inserer.");
    }

    public function edit($id) {
        $mois = Mois::find($id);

        return view("crud.moiss.edit", ["mois"=> $mois]);
    }

    public function update(Request $request, $id) {
        $mois = Mois::find($id);
        $mois->update([

            "m_designation"=> $request->input("m_designation")
            "m_position"=> $request->input("m_position")
        ]);

        return redirect()->route("moiss.index")->with("success","Mois mis ajour");
    }

    public function destroy($id) {
        $mois = Mois::find($id);
        $mois->delete();

        return redirect()->route("moiss.index")->with("success","Mois supprimer");
    }
}
