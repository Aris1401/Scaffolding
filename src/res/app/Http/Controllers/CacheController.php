<?php

namespace App\Http\Controllers;

use App\Models\Cache;
use Illuminate\Http\Request;

class CacheController extends Controller
{
    public function index() {
        $caches = Cache::paginate(10);

        return view("crud.caches.index", ["caches" => $caches]);
    }

    public function create() {
        return view("crud.caches.create");
    }

    public function store(Request $request) {
        $cache = Cache::create([

            "value" => $request->input("value")
            "expiration" => $request->input("expiration")
        ]);

        return redirect()->route("caches.index")->with("success","Cache inserer.");
    }

    public function edit($id) {
        $cache = Cache::find($id);

        return view("crud.caches.edit", ["cache"=> $cache]);
    }

    public function update(Request $request, $id) {
        $cache = Cache::find($id);
        $cache->update([

            "value"=> $request->input("value")
            "expiration"=> $request->input("expiration")
        ]);

        return redirect()->route("caches.index")->with("success","Cache mis ajour");
    }

    public function destroy($id) {
        $cache = Cache::find($id);
        $cache->delete();

        return redirect()->route("caches.index")->with("success","Cache supprimer");
    }
}
