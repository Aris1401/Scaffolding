<?php

namespace App\Http\Controllers;

use App\Models\Sessions;
use Illuminate\Http\Request;

class SessionsController extends Controller
{
    public function index() {
        $sessionss = Sessions::paginate(10);

        return view("crud.sessionss.index", ["sessionss" => $sessionss]);
    }

    public function create() {
        return view("crud.sessionss.create");
    }

    public function store(Request $request) {
        $sessions = Sessions::create([

            "user_id" => $request->input("user_id")
            "ip_address" => $request->input("ip_address")
            "user_agent" => $request->input("user_agent")
            "payload" => $request->input("payload")
            "last_activity" => $request->input("last_activity")
        ]);

        return redirect()->route("sessionss.index")->with("success","Sessions inserer.");
    }

    public function edit($id) {
        $sessions = Sessions::find($id);

        return view("crud.sessionss.edit", ["sessions"=> $sessions]);
    }

    public function update(Request $request, $id) {
        $sessions = Sessions::find($id);
        $sessions->update([

            "user_id"=> $request->input("user_id")
            "ip_address"=> $request->input("ip_address")
            "user_agent"=> $request->input("user_agent")
            "payload"=> $request->input("payload")
            "last_activity"=> $request->input("last_activity")
        ]);

        return redirect()->route("sessionss.index")->with("success","Sessions mis ajour");
    }

    public function destroy($id) {
        $sessions = Sessions::find($id);
        $sessions->delete();

        return redirect()->route("sessionss.index")->with("success","Sessions supprimer");
    }
}
