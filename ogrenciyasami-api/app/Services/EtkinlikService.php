<?php
namespace App\Services;
use App\Models\Etkinlik;
use App\Repositories\EtkinlikRepository;
use Illuminate\Support\Facades\Validator;
use InvalidArgumentException;
use Exception;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Log;

class EtkinlikService
{
    /**
     * @var $etkinlikRepository
     */

    protected $etkinlikRepository;

    /**
     * EtkinlikRepository constructor.
     * @param EtkinlikRepository $etkinlikRepository
     */

    public function __construct(EtkinlikRepository $etkinlikRepository)
    {
        $this->etkinlikRepository = $etkinlikRepository;
    }

    public function storeEtkinlik($data)
    {

       $validator=Validator::make ($data,[
            'etkinlik_adi' => 'required',
            'aciklama'=>'required',
            'etkinlik_tarihi'=>'required'
           // 'ucret'=>'required'

        ]);

        if($validator->fails())
        {
            throw new InvalidArgumentException($validator->errors()->first());
        }

        $result=$this->etkinlikRepository->store($data);
        return $result;
    }

    public function getEtkinlik()
    {
        return $this->etkinlikRepository->index();
    }

    public function showByIdEtkinlik($id)
    {
        try {
            $etkinlik =$this->etkinlikRepository->show($id);
        } catch (Exception $exception) {
            return $exception->getMessage();
        }
        return $etkinlik;
    }

    public function updateEtkinlik($data,$id)
    {
        /*
         * validator düşünülecek
         * */
        DB::beginTransaction();

        try {
            $etkinlik=$this->etkinlikRepository->update($data,$id);
        }catch (Exception $e){
            DB::rollBack();
            Log::info($e->getMessage());
            throw new InvalidArgumentException('Unable to update etkinlik data');
        }
        DB::commit();
        return $etkinlik;
    }

    public function deleteEtkinlik($id)
    {
        try {
            $etkinlik=$this->etkinlikRepository->delete($id);
        }catch (Exception $e){
            //DB::rollBack();
            Log::info($e->getMessage());
            throw new InvalidArgumentException('Unable to delete etkinlik data');
        }
        return $etkinlik;

    }
}

