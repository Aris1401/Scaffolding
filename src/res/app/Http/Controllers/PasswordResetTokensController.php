<?php

namespace App\Http\Controllers;

use App\Models\PasswordResetTokens;
use Illuminate\Http\Request;

class PasswordResetTokensController extends Controller
{
    public function index() {
        $password_reset_tokenss = PasswordResetTokens::paginate(10);

        return view("crud.password_reset_tokenss.index", ["password_reset_tokenss" => $password_reset_tokenss]);
    }

    public function create() {
        return view("crud.password_reset_tokenss.create");
    }

    public function store(Request $request) {
        $password_reset_tokens = PasswordResetTokens::create([

            "token" => $request->input("token")
            "created_at" => $request->input("created_at")
        ]);

        return redirect()->route("password_reset_tokenss.index")->with("success","PasswordResetTokens inserer.");
    }

    public function edit($id) {
        $password_reset_tokens = PasswordResetTokens::find($id);

        return view("crud.password_reset_tokenss.edit", ["password_reset_tokens"=> $password_reset_tokens]);
    }

    public function update(Request $request, $id) {
        $password_reset_tokens = PasswordResetTokens::find($id);
        $password_reset_tokens->update([

            "token"=> $request->input("token")
            "created_at"=> $request->input("created_at")
        ]);

        return redirect()->route("password_reset_tokenss.index")->with("success","PasswordResetTokens mis ajour");
    }

    public function destroy($id) {
        $password_reset_tokens = PasswordResetTokens::find($id);
        $password_reset_tokens->delete();

        return redirect()->route("password_reset_tokenss.index")->with("success","PasswordResetTokens supprimer");
    }
}
