<?php

namespace App\Http\Controllers;

use App\Models\JobBatches;
use Illuminate\Http\Request;

class JobBatchesController extends Controller
{
    public function index() {
        $job_batchess = JobBatches::paginate(10);

        return view("crud.job_batchess.index", ["job_batchess" => $job_batchess]);
    }

    public function create() {
        return view("crud.job_batchess.create");
    }

    public function store(Request $request) {
        $job_batches = JobBatches::create([

            "name" => $request->input("name")
            "total_jobs" => $request->input("total_jobs")
            "pending_jobs" => $request->input("pending_jobs")
            "failed_jobs" => $request->input("failed_jobs")
            "failed_job_ids" => $request->input("failed_job_ids")
            "options" => $request->input("options")
            "cancelled_at" => $request->input("cancelled_at")
            "created_at" => $request->input("created_at")
            "finished_at" => $request->input("finished_at")
        ]);

        return redirect()->route("job_batchess.index")->with("success","JobBatches inserer.");
    }

    public function edit($id) {
        $job_batches = JobBatches::find($id);

        return view("crud.job_batchess.edit", ["job_batches"=> $job_batches]);
    }

    public function update(Request $request, $id) {
        $job_batches = JobBatches::find($id);
        $job_batches->update([

            "name"=> $request->input("name")
            "total_jobs"=> $request->input("total_jobs")
            "pending_jobs"=> $request->input("pending_jobs")
            "failed_jobs"=> $request->input("failed_jobs")
            "failed_job_ids"=> $request->input("failed_job_ids")
            "options"=> $request->input("options")
            "cancelled_at"=> $request->input("cancelled_at")
            "created_at"=> $request->input("created_at")
            "finished_at"=> $request->input("finished_at")
        ]);

        return redirect()->route("job_batchess.index")->with("success","JobBatches mis ajour");
    }

    public function destroy($id) {
        $job_batches = JobBatches::find($id);
        $job_batches->delete();

        return redirect()->route("job_batchess.index")->with("success","JobBatches supprimer");
    }
}
