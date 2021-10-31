<?php


namespace App\Repositories;
use App\Models\Etkinlik;
use Illuminate\Database\Eloquent\ModelNotFoundException;
use Illuminate\Http\Request;

class EtkinlikRepository
{
    /**
     * @var Etkinlik
     */

    protected $etkinlik;

    /**
     * EtkinlikRepository constructor.
     * @param Etkinlik $etkinlik
     */


    public function __construct(Etkinlik $etkinlik)
    {
        $this->etkinlik = $etkinlik;
    }

    public function store($data)
    {
        $etkinlik = $this->etkinlik->create($data);
        return $etkinlik->id;
    }

    public function index()
    {
         $etkinlikler= $this->etkinlik->all();
         return $etkinlikler[0];
    }

    public function show($id)
    {

        $etkinlik = $this->etkinlik->find($id);
        if($etkinlik == null)
            throw new ModelNotFoundException();
        return $etkinlik;
    }

    public function update($data,$id)
    {
        $etkinlik=Etkinlik::find($id);
        if($etkinlik == null)
            throw new ModelNotFoundException();

        $etkinlik -> update($data);
        return $etkinlik;

    }

    public function delete($id)
    {
        $etkinlik=Etkinlik::find($id);
        if($etkinlik == null)
            throw new ModelNotFoundException();

        return $etkinlik-> delete();


    }
}
