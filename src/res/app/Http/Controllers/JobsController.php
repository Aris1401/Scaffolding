<?php

namespace App\Http\Controllers;

use App\Models\Jobs;
use Illuminate\Http\Request;

class JobsController extends Controller
{
    public function index() {
        $jobss = Jobs::paginate(10);

        return view("crud.jobss.index", ["jobss" => $jobss]);
    }

    public function create() {
        return view("crud.jobss.create");
    }

    public function store(Request $request) {
        $jobs = Jobs::create([

            "queue" => $request->input("queue")
            "payload" => $request->input("payload")
            "attempts" => $request->input("attempts")
            "reserved_at" => $request->input("reserved_at")
            "available_at" => $request->input("available_at")
            "created_at" => $request->input("created_at")
        ]);

        return redirect()->route("jobss.index")->with("success","Jobs inserer.");
    }

    public function edit($id) {
        $jobs = Jobs::find($id);

        return view("crud.jobss.edit", ["jobs"=> $jobs]);
    }

    public function update(Request $request, $id) {
        $jobs = Jobs::find($id);
        $jobs->update([

            "queue"=> $request->input("queue")
            "payload"=> $request->input("payload")
            "attempts"=> $request->input("attempts")
            "reserved_at"=> $request->input("reserved_at")
            "available_at"=> $request->input("available_at")
            "created_at"=> $request->input("created_at")
        ]);

        return redirect()->route("jobss.index")->with("success","Jobs mis ajour");
    }

    public function destroy($id) {
        $jobs = Jobs::find($id);
        $jobs->delete();

        return redirect()->route("jobss.index")->with("success","Jobs supprimer");
    }
}
