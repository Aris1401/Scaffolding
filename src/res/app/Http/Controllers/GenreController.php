<?php

namespace App\Http\Controllers;

use App\Models\Genre;
use Illuminate\Http\Request;

class GenreController extends Controller
{
    public function index() {
        $genres = Genre::paginate(10);

        return view("crud.genres.index", ["genres" => $genres]);
    }

    public function create() {
        return view("crud.genres.create");
    }

    public function store(Request $request) {
        $genre = Genre::create([

            "g_designation" => $request->input("g_designation")
        ]);

        return redirect()->route("genres.index")->with("success","Genre inserer.");
    }

    public function edit($id) {
        $genre = Genre::find($id);

        return view("crud.genres.edit", ["genre"=> $genre]);
    }

    public function update(Request $request, $id) {
        $genre = Genre::find($id);
        $genre->update([

            "g_designation"=> $request->input("g_designation")
        ]);

        return redirect()->route("genres.index")->with("success","Genre mis ajour");
    }

    public function destroy($id) {
        $genre = Genre::find($id);
        $genre->delete();

        return redirect()->route("genres.index")->with("success","Genre supprimer");
    }
}
