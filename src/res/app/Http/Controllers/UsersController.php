<?php

namespace App\Http\Controllers;

use App\Models\Users;
use Illuminate\Http\Request;

class UsersController extends Controller
{
    public function index() {
        $userss = Users::paginate(10);

        return view("crud.userss.index", ["userss" => $userss]);
    }

    public function create() {
        return view("crud.userss.create");
    }

    public function store(Request $request) {
        $users = Users::create([

            "name" => $request->input("name")
            "email" => $request->input("email")
            "email_verified_at" => $request->input("email_verified_at")
            "password" => $request->input("password")
            "remember_token" => $request->input("remember_token")
            "created_at" => $request->input("created_at")
            "updated_at" => $request->input("updated_at")
        ]);

        return redirect()->route("userss.index")->with("success","Users inserer.");
    }

    public function edit($id) {
        $users = Users::find($id);

        return view("crud.userss.edit", ["users"=> $users]);
    }

    public function update(Request $request, $id) {
        $users = Users::find($id);
        $users->update([

            "name"=> $request->input("name")
            "email"=> $request->input("email")
            "email_verified_at"=> $request->input("email_verified_at")
            "password"=> $request->input("password")
            "remember_token"=> $request->input("remember_token")
            "created_at"=> $request->input("created_at")
            "updated_at"=> $request->input("updated_at")
        ]);

        return redirect()->route("userss.index")->with("success","Users mis ajour");
    }

    public function destroy($id) {
        $users = Users::find($id);
        $users->delete();

        return redirect()->route("userss.index")->with("success","Users supprimer");
    }
}
