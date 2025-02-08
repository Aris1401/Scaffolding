<?php

namespace App\Http\Controllers;

use App\Models\Migrations;
use Illuminate\Http\Request;

class MigrationsController extends Controller
{
    public function index() {
        $migrationss = Migrations::paginate(10);

        return view("crud.migrationss.index", ["migrationss" => $migrationss]);
    }

    public function create() {
        return view("crud.migrationss.create");
    }

    public function store(Request $request) {
        $migrations = Migrations::create([

            "migration" => $request->input("migration")
            "batch" => $request->input("batch")
        ]);

        return redirect()->route("migrationss.index")->with("success","Migrations inserer.");
    }

    public function edit($id) {
        $migrations = Migrations::find($id);

        return view("crud.migrationss.edit", ["migrations"=> $migrations]);
    }

    public function update(Request $request, $id) {
        $migrations = Migrations::find($id);
        $migrations->update([

            "migration"=> $request->input("migration")
            "batch"=> $request->input("batch")
        ]);

        return redirect()->route("migrationss.index")->with("success","Migrations mis ajour");
    }

    public function destroy($id) {
        $migrations = Migrations::find($id);
        $migrations->delete();

        return redirect()->route("migrationss.index")->with("success","Migrations supprimer");
    }
}
