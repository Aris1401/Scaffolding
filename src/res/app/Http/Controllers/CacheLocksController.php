<?php

namespace App\Http\Controllers;

use App\Models\CacheLocks;
use Illuminate\Http\Request;

class CacheLocksController extends Controller
{
    public function index() {
        $cache_lockss = CacheLocks::paginate(10);

        return view("crud.cache_lockss.index", ["cache_lockss" => $cache_lockss]);
    }

    public function create() {
        return view("crud.cache_lockss.create");
    }

    public function store(Request $request) {
        $cache_locks = CacheLocks::create([

            "owner" => $request->input("owner")
            "expiration" => $request->input("expiration")
        ]);

        return redirect()->route("cache_lockss.index")->with("success","CacheLocks inserer.");
    }

    public function edit($id) {
        $cache_locks = CacheLocks::find($id);

        return view("crud.cache_lockss.edit", ["cache_locks"=> $cache_locks]);
    }

    public function update(Request $request, $id) {
        $cache_locks = CacheLocks::find($id);
        $cache_locks->update([

            "owner"=> $request->input("owner")
            "expiration"=> $request->input("expiration")
        ]);

        return redirect()->route("cache_lockss.index")->with("success","CacheLocks mis ajour");
    }

    public function destroy($id) {
        $cache_locks = CacheLocks::find($id);
        $cache_locks->delete();

        return redirect()->route("cache_lockss.index")->with("success","CacheLocks supprimer");
    }
}
