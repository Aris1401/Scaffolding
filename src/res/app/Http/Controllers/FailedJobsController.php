<?php

namespace App\Http\Controllers;

use App\Models\FailedJobs;
use Illuminate\Http\Request;

class FailedJobsController extends Controller
{
    public function index() {
        $failed_jobss = FailedJobs::paginate(10);

        return view("crud.failed_jobss.index", ["failed_jobss" => $failed_jobss]);
    }

    public function create() {
        return view("crud.failed_jobss.create");
    }

    public function store(Request $request) {
        $failed_jobs = FailedJobs::create([

            "uuid" => $request->input("uuid")
            "connection" => $request->input("connection")
            "queue" => $request->input("queue")
            "payload" => $request->input("payload")
            "exception" => $request->input("exception")
            "failed_at" => $request->input("failed_at")
        ]);

        return redirect()->route("failed_jobss.index")->with("success","FailedJobs inserer.");
    }

    public function edit($id) {
        $failed_jobs = FailedJobs::find($id);

        return view("crud.failed_jobss.edit", ["failed_jobs"=> $failed_jobs]);
    }

    public function update(Request $request, $id) {
        $failed_jobs = FailedJobs::find($id);
        $failed_jobs->update([

            "uuid"=> $request->input("uuid")
            "connection"=> $request->input("connection")
            "queue"=> $request->input("queue")
            "payload"=> $request->input("payload")
            "exception"=> $request->input("exception")
            "failed_at"=> $request->input("failed_at")
        ]);

        return redirect()->route("failed_jobss.index")->with("success","FailedJobs mis ajour");
    }

    public function destroy($id) {
        $failed_jobs = FailedJobs::find($id);
        $failed_jobs->delete();

        return redirect()->route("failed_jobss.index")->with("success","FailedJobs supprimer");
    }
}
